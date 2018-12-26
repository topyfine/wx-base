package top.yfine.wxbase.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.yfine.wxbase.api.HttpAgent;
import top.yfine.wxbase.api.request.TicketRequest;
import top.yfine.wxbase.api.request.TokenRequest;
import top.yfine.wxbase.api.response.TicketResponse;
import top.yfine.wxbase.api.response.TokenResponse;
import top.yfine.wxbase.constant.RedisKeyConstants;
import top.yfine.wxbase.service.WechatService;
import top.yfine.wxbase.util.SignatureUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author topyfine
 * @date 18-12-25 下午11:31
 */
@Service
public class WechatServiceImpl implements WechatService {
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Autowired
    private HttpAgent httpAgent;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Scheduled(fixedDelayString = "${wechat.token-refresh-rate}")
    public void automaticRefreshToken() {
//        refreshToken();
    }

    @Override
    public void refreshToken() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.addTextParam("appid", appId).addTextParam("secret", appSecret);
        TokenResponse tokenResponse = httpAgent.action(tokenRequest);
        // 成功获取access_token之后，去获取jsapi_ticket
        // 并执行全局缓存，供所有接口使用
        if (tokenResponse.isSuccess()) {
            redisTemplate.opsForValue().set(RedisKeyConstants.MP_ACCESS_TOKEN, tokenResponse.getAccessToken());
            TicketRequest ticketRequest = new TicketRequest();
            ticketRequest.addTextParam("access_token", tokenResponse.getAccessToken())
                    .addTextParam("type", "jsapi");
            TicketResponse ticketResponse = httpAgent.action(ticketRequest);
            redisTemplate.opsForValue().set(RedisKeyConstants.MP_JSAPI_TICKET, ticketResponse.getTicket());
        }
    }

    @Override
    public String jsSdkConfig(String url) {
        long time = System.currentTimeMillis();
        // 去除毫秒取10位长度
        String timestamp = StringUtils.truncate(String.valueOf(time), 10);
        String noncestr = RandomStringUtils.randomAlphanumeric(20);
        String ticket = redisTemplate.opsForValue().get(RedisKeyConstants.MP_JSAPI_TICKET);
        Map<String, String> params = new HashMap<>();
        params.put("noncestr", noncestr);
        params.put("jsapi_ticket", ticket);
        params.put("timestamp", timestamp);
        params.put("url", url);
        String signature = SignatureUtils.sign(params);
        // 返回结果
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", appId);
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("nonceStr", noncestr);
        jsonObject.put("signature", signature);
        return jsonObject.toJSONString();
    }
}
