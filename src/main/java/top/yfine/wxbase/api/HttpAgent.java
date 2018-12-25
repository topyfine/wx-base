package top.yfine.wxbase.api;

/**
 * @author 杨帆
 * @date 2018/12/25 11:52
 */
public interface HttpAgent {
    /**
     * 接口调用
     *
     * @param request 接口
     * @param <T>
     * @return 接口响应结果
     */
    <T extends BaseResponse> T action(BaseRequest<T> request);
}
