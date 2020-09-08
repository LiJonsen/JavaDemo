package com.josen.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.Map;
/**
 * http请求工具类
 */
public class HttpUtil {
    private static final MediaType CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");
    /**
     * 发起get请求
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送Post请求
     * @param url
     * @param reqBody 请求参数
     * @return
     */
    public static String httpPost(String url, Map<String,String> reqBody) {
        String result = null;
        // 创建客户端
        OkHttpClient client = new OkHttpClient();
        // 创建表单
//        FormBody.Builder bodyBuilder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : reqBody.entrySet()) {
//            bodyBuilder.add(entry.getKey(),entry.getValue());
//        }

        // 创建请求体对象
//        RequestBody body = bodyBuilder.build();
        String json = JSON.toJSONString(reqBody);
        RequestBody body = RequestBody.create(CONTENT_TYPE, json);
        // 创建请求参数
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
