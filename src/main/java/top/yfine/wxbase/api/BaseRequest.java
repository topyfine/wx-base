package top.yfine.wxbase.api;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author 杨帆
 * @date 2018/12/25 11:25
 */
public interface BaseRequest<T extends BaseResponse> {
    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    Class<T> getResponseClass();

    /**
     * 向当前api添加请求参数，支持链式调用
     *
     * @param name  请求参数名
     * @param value 请求参数值
     * @return 存储参数的集合
     */
    BaseRequest addTextParam(String name, String value);

    /**
     * 获取经过http封装的接口请求
     *
     * @return http封装的接口
     */
    HttpUriRequest getHttpUriRequest();
}
