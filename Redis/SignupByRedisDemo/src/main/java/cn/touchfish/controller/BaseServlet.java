package cn.touchfish.controller;
import cn.touchfish.beans.Result;
import cn.touchfish.utils.CommonUtils;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.ServletUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseServlet
 * @Description 通用客户端请求处理（全局只有一个Servlet服务）
 * @Author Josen
 * @Create 2020/8/11 9:46
 */

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Map<String,Object> params = new HashMap<>();
        if("POST".equals(req.getMethod())){

            // 处理Post请求数据
            String data = ServletUtils.getPostData(req);
            params = (Map)JSON.parse(data);
            action = (String)params.get("action");

            // 校验Token
            String path = req.getRequestURI();
            if("/home".equals(path)){
                String username = (String)params.get("username");
                String token = (String)params.get("token");
                boolean isLogin = validateTokenInfo(username, token);
                if(!isLogin){// Token校验失败
                    Result res = new Result(501, "token已过期，请重新登陆!", "");
                    ServletUtils.executeResponseJSON(resp,res);
                    return;
                }
            }
        }

        // 通过反射，动态的执行子类实现的对应方法（子类方法对应接口路径名）
        try {
            //  获取当前运行时类的方法名=action的方法
           Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class,Map.class);
           method.invoke(this,req,resp,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验登录后接口的Token缓存信息
     * @param username
     * @param token
     * @return
     */
    private boolean validateTokenInfo(String username,String token){
        if(username == null || "".equals(username) || token == null || "".equals(token)){
            return false;
        }
        String tokenKey = CommonUtils.getRedisTokenKey(username);
        String str = JedisUtils.getJedisCmd().ex_get(tokenKey);
        if(!token.equals(str)){
            return false;
        }
        return true;

    }
}
