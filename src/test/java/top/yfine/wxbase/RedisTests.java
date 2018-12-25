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
        String token = "16_SHs4mWxUHxQpgRobbCV37tSJcAzsUrb7dIf7X6_CTkipzeZzHXSGHKkg5Bi_lviwz7VTeg9gwXM-BLtj-3GG5vudQ9PTUkRgZT4DMAYepuxWwB2qlFtlFQl_XPZ_0MJUwoE-R8wep1ts2byePWScAHAHOK";
        Assert.assertEquals(token, template.opsForValue().get(RedisKeyConstants.MP_ACCESS_TOKEN));
    }

    @Test
    public void testTicket() {
        String ticket = "HoagFKDcsGMVCIY2vOjf9qhVQVhybwyQDkhjqGDnLkXWA7y3bP378dqLWYvYNjJWoh6lmABYyqRVbet3w_8bDw";
        Assert.assertEquals(ticket, template.opsForValue().get(RedisKeyConstants.MP_JSAPI_TICKET));
    }
}
