package top.yfine.wxbase.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.yfine.wxbase.constant.RedisKeyConstants;
import top.yfine.wxbase.domain.WxConfigVO;

/**
 * @author 杨帆
 * @date 2018/12/26 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatServiceTests {
    @Autowired
    private WechatService wechatService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRefreshToken() {
        wechatService.refreshToken();
        Assert.assertNotNull(redisTemplate.opsForValue().get(RedisKeyConstants.MP_ACCESS_TOKEN));
        Assert.assertNotNull(redisTemplate.opsForValue().get(RedisKeyConstants.MP_JSAPI_TICKET));
    }

    @Test
    public void testJsSdkConfig() {
        WxConfigVO wxConfigVO = wechatService.jsSdkConfig("http://192.168.4.172:8080/");
        System.out.println(wxConfigVO);
        Assert.assertNotNull(wxConfigVO);
    }
}
