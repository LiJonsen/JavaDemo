package com.atguigu.servlet;

import javax.servlet.*;
import java.io.IOException;
//Servlet生命周期
public class HelloServlet implements Servlet {

    public HelloServlet(){
        System.out.println("HelloServlet 构造器");
    }

    /**
     * 初始化-游览器url第一次访问时调用
     * @param servletConfig 作用：获取初始化参数
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet Init");
        // 1. 获取程序别名
        System.out.println("Servlet别名："+servletConfig.getServletName());
        // 2. 获取web.xml中配置的init-param标签值
        String username = servletConfig.getInitParameter("username");
        String url = servletConfig.getInitParameter("url");
        System.out.println("init-param标签username初始值："+username);
        System.out.println("init-param标签url初始值："+url);
        // 3. 获取Servlet Context对象
        System.out.println("servlet Context对象："+servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service方法专门用来处理请求和响应-每次请求调用一次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet Service");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet Destroy");
    }
}
