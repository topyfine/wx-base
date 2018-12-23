package top.yfine.wxbase.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.yfine.wxbase.service.MpTokenService;
import top.yfine.wxbase.util.HttpUtils;

import java.net.URI;
import java.net.URISyntaxException;

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

    @Override
    @Scheduled(fixedRateString = "${wechat.token-refresh-rate}")
    public void autoRefreshToken() {
        refreshToken();
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
            String result = EntityUtils.toString(httpResponse.getEntity());
            log.info(">>> 接口 {}", uri);
            log.info(">>> 响应 {}", result);
        } catch (Exception e) {
            log.error(">>> 获取access_token异常", e);
        }
    }
}
