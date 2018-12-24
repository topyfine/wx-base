package top.yfine.wxbase.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 杨帆
 * @Date 2018/12/24 11:29
 * 响应反序列化对象
 */
@Data
@ToString
public class BaseResult {
    @JSONField(serialize = false, deserialize = false)
    private String originalResult;
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;
}
