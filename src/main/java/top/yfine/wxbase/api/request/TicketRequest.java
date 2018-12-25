package top.yfine.wxbase.api.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import top.yfine.wxbase.api.BaseRequest;
import top.yfine.wxbase.api.response.TicketResponse;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author topyfine
 * @date 18-12-25 下午10:34
 */
@Slf4j
public class TicketRequest implements BaseRequest<TicketResponse> {
    private Map<String, String> textParams = new HashMap<>();
    @Override
    public Class<TicketResponse> getResponseClass() {
        return TicketResponse.class;
    }

    @Override
    public BaseRequest addTextParam(String name, String value) {
        textParams.put(name, value);
        return this;
    }

    @Override
    public HttpUriRequest getHttpUriRequest() {
        URIBuilder builder = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPath("/cgi-bin/ticket/getticket");
        for (Map.Entry<String, String> entry : textParams.entrySet()) {
            builder.setParameter(entry.getKey(), entry.getValue());
        }
        // 接口请求方式GET
        HttpGet httpGet = new HttpGet();
        try {
            httpGet.setURI(builder.build());
        } catch (URISyntaxException e) {
            log.error(">>> jsapi_ticket uri build error.");
        }
        return httpGet;
    }
}
