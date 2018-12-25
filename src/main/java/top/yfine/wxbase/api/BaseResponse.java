package top.yfine.wxbase.api;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杨帆
 * @date 2018/12/25 11:25
 */
@Data
@ToString
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 3334606701011459855L;
    @JSONField(serialize = false, deserialize = false)
    private String originalResult;
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;

    /**
     * 响应结果不为空且错误码标识正常，返回true;
     * 否则，返回false.
     *
     * @return true/false 响应结果
     */
    public boolean isSuccess() {
        if (originalResult == null) {
            return false;
        }
        return errCode == null || "0".equals(errCode);
    }
}
