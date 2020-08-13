package cn.touchfish.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @ClassName CommonUtils
 * @Description Servlet工具类
 * @Author Josen
 * @Create 2020/8/11 10:46
 */
public final class ServletUtils {
    public static String getHostUrl(HttpServletRequest req){
        return req.getScheme()+"://"+ req.getServerName()+":"+req.getServerPort()+"/";
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
