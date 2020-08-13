package com.josen.beans;

import java.util.Map;

/**
 * @ClassName ResponseData
 * @Description 响应数据Bean
 * @Author Josen
 * @Create 2020/8/1 18:21
 */
public class ResponseData{
    public static final String SUCCESS_MSG = "response finish!";
    public static final int SUCCESS_CODE = 200;
    // 响应码
    private int code = SUCCESS_CODE;
    // data：响应key-value键值对数据
    // Map的值必须为集合类型
    // ? extends Collection - 必须是Collection或其子类型
    private Map<String,?> data;

    // 响应提示信息
    private String message = SUCCESS_MSG;



    public ResponseData() {
    }

    public ResponseData(int code, String message, Map<String, ?> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }
}
