package com.bookstore.web.servlet.controller;

import com.bookstore.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.bookstore.service.user.UserServiceImpl;
import com.bookstore.utils.WebUtils;

/**
 * @ClassName UserServlet
 * @Description 将登录、注册请求合并处理
 * @Author Josen
 * @Date 2020/6/9 15:53
 * @Version 1.0
 **/
public class UserServlet extends BaseServlet{
    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * 处理用户登录请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 验证码校验不通过
//        if(!WebUtils.CheckedCode(req)){
//            req.setAttribute("msg","验证码错误!");
//            req.setAttribute("username",username);
//            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
//            return;
//        }

        User user = userService.queryUsernameAndPsd(new User(0, username, password, null));

        // 登录校验通过
        if(user != null){
            req.getSession().setAttribute("username",username);
            req.getRequestDispatcher("").forward(req, resp);

        }else{
            // 校验不通过
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 处理退出登录请求
     * @param req
     * @param resp
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp){
        req.getSession().invalidate();
        try {
            resp.sendRedirect(req.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理用户注册请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println("username:"+username);
        User user = userService.queryUsername(username);

        // 用户已存在-注册失败
        if(user != null){
            req.setAttribute("msg","用户名已存在!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }

        // 验证码校验不通过
        if(!WebUtils.CheckedCode(req)){
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            return;
        }

        String password = req.getParameter("password");
        String email = req.getParameter("email");

        int res = userService.registerAccount(new User(0, username, password, email));
        System.out.println("res:"+res);
        // 注册成功
        if(res>0){
            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            return;
        }
        // 注册失败
        req.setAttribute("msg","注册失败，请刷新重试!");
        req.setAttribute("username",username);
        req.setAttribute("email",email);
        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
    }


}
