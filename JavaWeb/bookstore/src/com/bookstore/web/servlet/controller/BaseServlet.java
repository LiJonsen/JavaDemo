package com.bookstore.web.servlet.controller;

import com.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ClassName BaseServlet
 * @Description 通用客户端请求处理（全局只有一个Servlet服务）
 * @Author Josen
 * @Date 2020/6/9 15:48
 * @Version 1.0
 **/
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        WebUtils.RemoveCommonResMsg(req);
        // 使用反射-根据action值执行不同的接口业务处理
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出错误，让Filter捕获
            throw new RuntimeException(e);
        }
    }
}
