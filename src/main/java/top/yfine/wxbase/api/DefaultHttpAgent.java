package top.yfine.wxbase.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import top.yfine.wxbase.util.LogUtils;
import top.yfine.wxbase.util.ResultUtils;

/**
 * @author 杨帆
 * @date 2018/12/25 11:55
 */
@Component
@Slf4j
public class DefaultHttpAgent implements HttpAgent {
    private CloseableHttpClient httpClient;

    {
        // do custom
        httpClient = HttpClients.createMinimal();
    }

    @Override
    public <T extends BaseResponse> T action(BaseRequest<T> request) {
        // http协议包装的接口
        HttpUriRequest httpUriRequest = request.getHttpUriRequest();
        T response = null;
        long beginTime = System.currentTimeMillis();
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpUriRequest)) {
            long endTime = System.currentTimeMillis();
            String content = EntityUtils.toString(httpResponse.getEntity());
            LogUtils.log(httpUriRequest.getURI(), content, endTime - beginTime);
            response = ResultUtils.parse(content, request.getResponseClass());
        } catch (Exception e) {
            log.error(">>> 接口调用失败 {}", httpUriRequest.getURI());
        }
        return response;
    }
}
