package top.yfine.wxbase.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;

/**
 * @Author 杨帆
 * @Date 2018/12/24 16:39
 */
@Slf4j
public class LogUtils {
    /**
     * 输出接口调用详情
     *
     * @param uri api
     * @param result 响应内容
     */
    public static void log(URI uri, String result) {
        log.info(">>> 接口 {}", uri);
        log.info(">>> 响应 {}", result);
    }
}
