package top.yfine.wxbase.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import top.yfine.wxbase.api.BaseResponse;

/**
 * @author 杨帆
 * @date 2018/12/25 11:35
 */
@Data
@ToString(callSuper = true)
public class TokenResponse extends BaseResponse {
    private static final long serialVersionUID = -7576266874474313888L;
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private String expiresIn;
}
