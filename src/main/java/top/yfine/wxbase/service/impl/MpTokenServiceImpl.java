package top.yfine.wxbase.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.yfine.wxbase.common.RedisKeyConstants;
import top.yfine.wxbase.result.TicketResult;
import top.yfine.wxbase.result.TokenResult;
import top.yfine.wxbase.service.MpTokenService;
import top.yfine.wxbase.util.HttpUtils;
import top.yfine.wxbase.util.LogUtils;
import top.yfine.wxbase.util.ResultUtils;

import java.net.URI;

/**
 * 微信公众号access_token/js_ticket
 * 换取服务接口实现
 */
@Service
@Slf4j
public class MpTokenServiceImpl implements MpTokenService {
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Scheduled(fixedRateString = "${wechat.token-refresh-rate}")
    public void autoRefreshToken() {
//        refreshToken();
    }

    @Override
    public void refreshToken() {
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.weixin.qq.com")
                    .setPath("/cgi-bin/token")
                    .setParameter("grant_type", "client_credential")
                    .setParameter("appid", appId)
                    .setParameter("secret", appSecret)
                    .build();
            CloseableHttpResponse httpResponse = HttpUtils.getClient().execute(new HttpGet(uri));
            String content = EntityUtils.toString(httpResponse.getEntity());
            LogUtils.log(uri, content);
            if (!ResultUtils.isNormal(content)) {
                throw new Exception(content);
            }
            TokenResult tokenResult = ResultUtils.parse(content, TokenResult.class);
            // 全局缓存access_token
            redisTemplate.opsForValue().set(RedisKeyConstants.MP_ACCESS_TOKEN, tokenResult.getAccessToken());
        } catch (Exception e) {
            log.error(">>> 获取access_token失败", e);
        }

        try {
            String token = redisTemplate.opsForValue().get(RedisKeyConstants.MP_ACCESS_TOKEN);
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.weixin.qq.com")
                    .setPath("/cgi-bin/ticket/getticket")
                    .setParameter("access_token", token)
                    .setParameter("type", "jsapi")
                    .build();
            CloseableHttpResponse httpResponse = HttpUtils.getClient().execute(new HttpGet(uri));
            String content = EntityUtils.toString(httpResponse.getEntity());
            LogUtils.log(uri, content);
            if (!ResultUtils.isNormal(content)) {
                throw new Exception(content);
            }
            TicketResult ticketResult = ResultUtils.parse(content, TicketResult.class);
            // 全局缓存jsapi_ticket
            redisTemplate.opsForValue().set(RedisKeyConstants.MP_JSAPI_TICKET, ticketResult.getTicket());
        } catch (Exception e) {
            log.error(">>> 获取jsapi_ticket失败", e);
        }
    }
}
