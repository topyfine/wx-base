package top.yfine.wxbase.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.MapUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author topyfine
 * @date 18-12-25 下午11:32
 */
@Slf4j
public class SignatureUtils {
    /**
     * 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
     * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
     * 这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义。
     *
     * @param params 参数集合
     * @return sha1加密生成的签名
     */
    public static String sign(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        // 对参数集合按照key进行字典排序
        params.keySet().stream().sorted().forEach(key -> {
            builder.append("&").append(key).append("=").append(params.get(key));
        });
        // 去除拼接结果第1个多余的“&”
        String string1 = builder.substring(1);
        String signstr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(string1.getBytes());
            byte[] digest = md.digest();
            signstr = Hex.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getLocalizedMessage());
        }
        return signstr;
    }
}
