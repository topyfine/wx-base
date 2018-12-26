package top.yfine.wxbase.service;

/**
 * @author topyfine
 * @date 18-12-25 下午11:30
 */
public interface WechatService {
    /**
     * 自动换取公众号access_token和jsapi_ticket并全局缓存
     */
    void automaticRefreshToken();

    /**
     * 被动换取公众号access_token和jsapi_ticket并全局缓存
     */
    void refreshToken();

    /**
     * 获取JS-SDK使用所需的配置信息
     * 签名生成规则如下：
     * 参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）,
     * url（当前网页的URL，不包含#及其后面部分） 。
     *
     * @param url 使用JS-SDK的当前页面
     * @return wx.config所需的配置信息
     */
    String jsSdkConfig(String url);
}
