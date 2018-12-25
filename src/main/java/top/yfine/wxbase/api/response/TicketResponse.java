package top.yfine.wxbase.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import top.yfine.wxbase.api.BaseResponse;

/**
 * @author topyfine
 * @date 18-12-25 下午10:31
 */
@Data
@ToString
public class TicketResponse extends BaseResponse {
    private static final long serialVersionUID = -8520422217705936860L;
    @JSONField(name = "ticket")
    private String ticket;
    @JSONField(name = "expires_in")
    private String expiresIn;
}
