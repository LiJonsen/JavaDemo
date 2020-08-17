package cn.touchfish.utils;

import cn.touchfish.beans.Result;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @ClassName CommonUtils
 * @Description Servlet工具类
 * @Author Josen
 * @Create 2020/8/11 10:46
 */
public final class ServletUtils {
    public static void executeResponseJSON(HttpServletResponse resp, Result res) throws IOException {
        String s = JSON.toJSONString(res);
        resp.getWriter().print(s);
    }
    public static String getHostUrl(HttpServletRequest req){
        String url = req.getScheme()+"://"+ req.getServerName()+":"+req.getServerPort()+"/";
        if(url.contains("localhost") || url.contains("127.0.0.1")){
            return url;
        }
        return "https://www.touchfish.cn/";
    }
    /**
     * 获取POST请求参数
     * @param request
     * @return
     */
    public static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())){
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}
