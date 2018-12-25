package top.yfine.wxbase.util;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * @author topyfine
 * @date 18-12-25 下午11:32
 * 签名生成规则如下：参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）,
 * url（当前网页的URL，不包含#及其后面部分） 。对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
 * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
 * 这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义。
 */
public class SignUtils {
    /**
     * 生成指定的参数集合的签名
     * @param params 参数集合
     * @return sha1加密生成的签名
     */
    public static String sign(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return null;
        }
        // 对参数集合按照key进行字典排序
        return null;
    }
}
