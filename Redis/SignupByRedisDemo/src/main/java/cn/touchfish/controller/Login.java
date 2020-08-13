package cn.touchfish.controller;
import cn.touchfish.beans.Codes;
import cn.touchfish.beans.Result;
import cn.touchfish.beans.SignUpAccount;
import cn.touchfish.service.LoginService;
import cn.touchfish.service.LoginServiceImpl;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.MailUtils;
import cn.touchfish.utils.ServletUtils;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;


/**
 * @ClassName Login
 * @Description 登录模块Servlet
 * @Author Josen
 * @Create 2020/8/10 21:21
 */
@WebServlet("/login")
public class Login extends BaseServlet {
    private LoginService loginService = new LoginServiceImpl();
    /**
     * 注册接口
     * @param req
     * @param resp
     * @param params
     * @throws IOException
     */
    protected void signUp(HttpServletRequest req, HttpServletResponse resp,Map<String,String> params) throws IOException {
        String username = params.get("username");
        String password = params.get("password");
        String email = params.get("email");

        // 注册用户操作
        SignUpAccount account = new SignUpAccount(username, password, email);
        String status = "注册失败，请稍后重试！";
        Result res = new Result(500, status, "");

        try {
            status = loginService.validateSignUpInfo(account);
            res.setMsg(status);
            // 注册成功-待激活
            if(Codes.SUCCESS_CODE.equals(status)){
                res.setCode(200);
                // 发送激活链接到用户邮箱，用户通过点击激活账号
                String code = UUID.randomUUID().toString();
                String url = ServletUtils.getHostUrl(req) + "active?username="+username+"&code="+code+"&action=activationUser";
                MailUtils mailUtils = new MailUtils(email, url,username);
                mailUtils.sendMail();
                Jedis jedis = JedisUtils.getJedis();
                // 1天缓存过期
                jedis.setex(username,24*60*60,code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String s = JSON.toJSONString(res);
        resp.getWriter().print(s);
    }

    /**
     * 登录接口
     * @param req
     * @param resp
     * @param params
     * @throws IOException
     */
    protected void signIn(HttpServletRequest req, HttpServletResponse resp,Map<String,String> params) throws IOException{
        String username = params.get("username");
        String password = params.get("password");
        Result res = new Result(500, "账号或密码错误！", "");
        try {
            String status = loginService.validateSignIn(username, password);
            res.setMsg(status);
            if(Codes.SUCCESS_CODE.equals(status)){
                // 校验通过，生成Token(存储在redis)
                String token = UUID.randomUUID().toString();
                Jedis jedis = JedisUtils.getJedis();
                jedis.setex(username+"_token",60*60*24,token);
                res.setData(token);
                res.setMsg("登录成功！");
                res.setCode(200);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String s = JSON.toJSONString(res);
        resp.getWriter().print(s);
    }


}
