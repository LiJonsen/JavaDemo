package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyFirstInterceptor
 * @Description 我的第一个自定义拦截器
 * @Author Josen
 * @Create 2020/7/31 11:30
 */
public class MyFirstInterceptor implements HandlerInterceptor {
    /**
     * 1. 是在DispatcherServlet的939行   在请求处理方法之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("Run preHandle");
        return true;
    }
    /**
     * 2. 在DispatcherServlet 959行   请求处理方法之后，视图处理之前执行。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Run postHandle");

    }
    /**
     * 3.
     * 	 [1].在DispatcherServlet的 1030行   视图处理之后执行.(转发/重定向后执行)
     * 	 [2].当有多个拦截器时，某个拦截器的preHandle返回false后，
     * 	     也会执行当前拦截器之前拦截器的afterCompletion
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("Run afterCompletion");
    }
}
