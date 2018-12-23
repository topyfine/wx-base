package top.yfine.wxbase.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 单例：饿汉式
 * 创建http client
 */
@Slf4j
public class HttpUtils {
    private static CloseableHttpClient instance = HttpClients.createMinimal();

    public HttpUtils() {
    }

    public static CloseableHttpClient getClient() {
        return instance;
    }

    public static void closeClient() {
        try {
            instance.close();
        } catch (Exception e) {
            log.error(">>> http client关闭异常", e);
        }
    }
}