package top.yfine.wxbase.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author topyfine
 * @date 18-12-26 下午10:42
 */
@Data
@ToString
public class WxConfigVO implements Serializable {
    private static final long serialVersionUID = -828551310773142499L;
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;
}
