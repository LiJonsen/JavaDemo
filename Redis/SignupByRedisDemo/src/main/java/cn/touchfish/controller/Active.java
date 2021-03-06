package cn.touchfish.controller;

import cn.touchfish.beans.Codes;
import cn.touchfish.beans.Result;
import cn.touchfish.service.LoginService;
import cn.touchfish.service.LoginServiceImpl;
import cn.touchfish.service.WebSiteService;
import cn.touchfish.service.WebSiteServiceImpl;
import cn.touchfish.utils.CommonUtils;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @ClassName Active
 * @Description 激活账户
 * @Author Josen
 * @Create 2020/8/12 17:23
 */
@WebServlet("/active")
public class Active extends BaseServlet{
    private LoginService loginService = new LoginServiceImpl();
    private WebSiteService webSiteService = new WebSiteServiceImpl();

    /**
     * 注册激活Get接口
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void activationUser(HttpServletRequest req, HttpServletResponse resp, Map<String,Object> params) throws IOException, SQLException {
        String username = req.getParameter("username");
        String code = req.getParameter("code");
        // 1. 从Redis获取code
        String activeName = CommonUtils.getRedisActiveName(username);
        String redis_code = JedisUtils.getJedisCmd().ex_get(activeName);
        Result res = new Result(500, "激活失败!", "");
        // 该用户已激活
        if(loginService.checkUserIsActive(username)){
            res.setCode(500);
            res.setMsg(Codes.CODE_1007);
        }else if(code.equals(redis_code)){
             // 激活账号
            String msg = loginService.activeAccount(username);
            res.setMsg(msg);
            if(Codes.SUCCESS_CODE.equals(msg)){
                // 3. 激活成功-清除redis缓存
                res.setCode(200);
                res.setMsg("激活成功！");
                JedisUtils.getJedisCmd().ex_del(activeName);
                webSiteService.updateRedisWebSiteMsg("active_count",username);
            }
        }
        // 将响应数据放到Cookie
        String str = username+"&"+res.getCode();
        Cookie cookie = new Cookie("activeCode", str);
        cookie.setMaxAge(60);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect(ServletUtils.getHostUrl(req));
    }
}
