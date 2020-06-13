package com.bookstore.pojo;

/**
 * @ClassName ResponseData
 * @Description 请求响应通用类
 * @Author Josen
 * @Date 2020/6/12 14:56
 * @Version 1.0
 **/
public class ResponseData<T> {
    // 提示信息
    private String msg;
    // 提示码
    private int code;
    // 响应数据载体
    private T data;

    public ResponseData() {
    }
    public ResponseData(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
