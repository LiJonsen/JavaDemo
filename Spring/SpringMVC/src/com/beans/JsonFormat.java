package com.beans;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName JsonFormat
 * @Description Details
 * @Author Josen
 * @Create 2020/7/30 21:06
 */
public class JsonFormat {
    private String message;
    private Object[] data;
    public JsonFormat() {
    }

    public JsonFormat(String message, Object[] data) {
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonFormat{" +
                "message='" + message + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
