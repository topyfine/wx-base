package top.yfine.wxbase.api.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import top.yfine.wxbase.api.BaseRequest;
import top.yfine.wxbase.api.response.TokenResponse;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨帆
 * @date 2018/12/25 11:33
 */
@Slf4j
public class TokenRequest implements BaseRequest<TokenResponse> {
    private Map<String, String> textParams = new HashMap<>();

    @Override
    public Class<TokenResponse> getResponseClass() {
        return TokenResponse.class;
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
                .setPath("/cgi-bin/token")
                .setParameter("grant_type", "client_credential");
        for (Map.Entry<String, String> entry : textParams.entrySet()) {
            builder.setParameter(entry.getKey(), entry.getValue());
        }
        // 接口请求方式GET
        HttpGet httpGet = new HttpGet();
        try {
            httpGet.setURI(builder.build());
        } catch (URISyntaxException e) {
            log.error(">>> access_token uri build error.");
        }
        return httpGet;
    }
}
