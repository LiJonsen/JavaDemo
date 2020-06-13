package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class TestRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post 请求");
        showParams(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get 请求");
//        ServletConfig servletConfig = getServletConfig();
//        String password = servletConfig.getInitParameter("password");
//        System.out.println("获取init-param标签值："+password);

        showParams(request);
    }

    public void showParams(HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取请求数据
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");

        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobby));
    }
}
