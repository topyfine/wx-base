package top.yfine.wxbase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yfine.wxbase.api.HttpAgent;
import top.yfine.wxbase.api.request.TokenRequest;
import top.yfine.wxbase.api.response.TokenResponse;

/**
 * @author 杨帆
 * @date 2018/12/25 15:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenRequestTests {
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;
    @Autowired
    private HttpAgent httpAgent;

    @Test
    public void test() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.addTextParam("appid", appId).addTextParam("secret", appSecret);
        TokenResponse tokenResponse = httpAgent.action(tokenRequest);
        Assert.assertNotNull(tokenResponse);
    }
}
