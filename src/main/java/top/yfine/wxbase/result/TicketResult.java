package top.yfine.wxbase.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 杨帆
 * @Date 2018/12/24 17:34
 */
@Data
@ToString
public class TicketResult extends BaseResult {
    @JSONField(name = "ticket")
    private String ticket;
    @JSONField(name = "expires_in")
    private String expiresIn;
}
