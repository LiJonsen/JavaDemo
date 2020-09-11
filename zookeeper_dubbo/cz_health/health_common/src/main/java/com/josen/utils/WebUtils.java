package com.josen.utils;

import com.josen.entity.MessageConstant;
import com.josen.entity.Result;

/**
 * @ClassName WebUtils
 * @Description Controller层工具类
 * @Author Josen
 * @Create 2020/9/10 18:09
 */
public class WebUtils {
    /**
     * 返回请求失败默认错误Message Result
     * @return
     */
    public static Result getFailResult(){
        return getFailResult(MessageConstant.REQ_PARAM_ERROR);
    }

    /**
     * 返回请求失败指定错误Message Result
     * @param message
     * @return
     */
    public static Result getFailResult(String message){
        return new Result(false, message, "");
    }

    /**
     * 返回请求成功Result
     * @return
     */
    public static Result getSuccessResult(String message,Object data){
        return new Result(true, message, data);
    }
    public static Result getSuccessResult(Object data){
        return getSuccessResult(MessageConstant.REQ_SUCCESS,data);
    }
    /**
     * 返回默认请求成功Result
     * @return
     */
    public static Result getSuccessResult(){
        return getSuccessResult(MessageConstant.REQ_SUCCESS,"");
    }
}
