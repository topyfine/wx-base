package top.yfine.wxbase.service;

/**
 * 微信公众号access_token/js_ticket
 * 换取服务接口
 */
public interface MpTokenService {
    /**
     * 主动刷新令牌
     */
    void autoRefreshToken();

    /**
     * 刷新令牌
     *
     * @url https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
     * 1.获取access_token;
     * https请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * 正常情况下，微信会返回下述JSON数据包给公众号：
     * {"access_token":"ACCESS_TOKEN","expires_in":7200}
     * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
     * {"errcode":40013,"errmsg":"invalid appid"}
     *
     * 2.获取js_ticket;
     * @url
     */
    void refreshToken();
}
