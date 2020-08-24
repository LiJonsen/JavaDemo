package cn.touchfish.mm.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.pojo.User;
import cn.touchfish.mm.service.LoginService;
import cn.touchfish.mm.utils.JedisUtils;
import cn.touchfish.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description
 * @Author Josen
 * @Create 2020/8/18 9:49
 */
@Controller
public class LoginController {
    private LoginService loginService = new LoginService();

    @RequestMapping("/login/signIn")
    public void signIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            User user = JsonUtils.parseJSON2Object(req, User.class);
            User data = loginService.validateSignIn(user);
            if (data!=null) {
                // 登录成功
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                data.setPassword(null);
                result.setResult(data);
                // 将用户名和userId存储到redis
                req.getSession().setAttribute("user",data);

//                JedisUtils.getJedisCmd().ex_hset(Constants.REDIS_LOGIN_KEY,data.getUsername(),data.getId().toString());

            }else{
                result.setMessage("账号或密码错误！");
            }
            JsonUtils.printResult(resp, result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp, result);
        }
    }

    /**
     * 退出登录
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/login/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = JsonUtils.parseJSON2Object(req, User.class);
        // 清除redis用户信息缓存
//        JedisUtils.getJedisCmd().ex_hdel(Constants.REDIS_LOGIN_KEY,user.getUsername());
        // 销毁服务端的session域对象
        req.getSession().invalidate();
        JsonUtils.printResult(resp,new Result(200,Constants.SERVICE_SUCCESS));
    }
}
