package top.yfine.wxbase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.yfine.wxbase.constant.RedisKeyConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    private StringRedisTemplate template;

    @Test
    public void test() {
        template.opsForValue().set("hello", "world");
        Assert.assertEquals("world", template.opsForValue().get("hello"));
    }

    @Test
    public void testToken() {
        String token = "17_0Lanp6ahqadVtOJ1FEQLcv5yfhQNjTb2S0ZRatcgq0nTABYvBZj1vZ3p8WmRoSxXpGb2dfUnubNKQzOOPweLR_Ttlomt6B-vofnFKJVaSbLDuzxQJSlNh6h0EXJIpD9LFntd6D9-EkJ0YsotFOVeAGABKR";
        Assert.assertEquals(token, template.opsForValue().get(RedisKeyConstants.MP_ACCESS_TOKEN));
    }

    @Test
    public void testTicket() {
        String ticket = "HoagFKDcsGMVCIY2vOjf9qhVQVhybwyQDkhjqGDnLkX5LxvnupoYv4rkDhf15S6_jcQOoOStFINrIHocyjK79Q";
        Assert.assertEquals(ticket, template.opsForValue().get(RedisKeyConstants.MP_JSAPI_TICKET));
    }
}
