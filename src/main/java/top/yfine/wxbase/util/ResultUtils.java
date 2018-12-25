package top.yfine.wxbase.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.yfine.wxbase.api.BaseResponse;

/**
 * @Author 杨帆
 * @Date 2018/12/24 14:35
 */
public class ResultUtils {
    /**
     * 判断接口响应是否正常
     *
     * @param result 接口响应内容
     * @return true/false
     */
    public static boolean isNormal(String result) {
        if (result == null) {
            return false;
        }
        JSONObject jsonObject = JSON.parseObject(result);
        return !jsonObject.containsKey("errcode") || jsonObject.getIntValue("errcode") == 0;
    }

    /**
     * 提供接口响应内容到实体的映射
     *
     * @param result 接口响应内容
     * @param clazz  metadata
     * @param <T>    实体类
     * @return T 实体
     */
    public static <T extends BaseResponse> T parse(String result, Class<T> clazz) {
        if (result == null) {
            return null;
        }
        T t = JSON.parseObject(result, clazz);
        t.setOriginalResult(result);
        return t;
    }
}
