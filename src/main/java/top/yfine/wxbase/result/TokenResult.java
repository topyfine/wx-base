package top.yfine.wxbase.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author 杨帆
 * @date 2018/12/24 11:44
 * @desc v1.0.0
 */
@Data
@ToString
public class TokenResult extends BaseResult {
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private String expiresIn;
}
