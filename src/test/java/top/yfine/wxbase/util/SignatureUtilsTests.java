package top.yfine.wxbase.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨帆
 * @date 2018/12/26 10:50
 */
public class SignatureUtilsTests {
    @Test
    public void test() {
        Map<String, String> params = new HashMap<>();
        params.put("noncestr", "Wm3WZYTPz0wzccnW");
        params.put("jsapi_ticket", "sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg");
        params.put("timestamp", "1414587457");
        params.put("url", "http://mp.weixin.qq.com?params=value");
        Assert.assertEquals("0f9de62fce790f9a083d5c99e95740ceb90c27ed", SignatureUtils.sign(params));
    }

    @Test
    public void testRandomString() {
        System.out.println(RandomStringUtils.random(20));
        System.out.println(RandomStringUtils.randomAscii(20));
        // 字母[a-z,A-Z]和数字[0-9]
        System.out.println(RandomStringUtils.randomAlphanumeric(20));
    }
}
