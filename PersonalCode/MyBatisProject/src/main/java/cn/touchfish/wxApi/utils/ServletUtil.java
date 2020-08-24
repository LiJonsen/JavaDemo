package cn.touchfish.wxApi.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ServletUtil
 * @Description Controller请求层工具类
 * @Author Josen
 * @Create 2020/8/23 20:41
 */
public final class ServletUtil {
    /**
     * 获取请求头用户Token
     * @return
     */
    public static String getReqHeaderToken(HttpServletRequest req) {
        try{
            String authorization = req.getHeader("Authorization");
            if (authorization != null && authorization.contains("Bearer")) {
                authorization = authorization.replace("Bearer ", "");
            }
            return authorization;
        }catch (RuntimeException e){
            e.printStackTrace();
            return null;
        }

    }
}
