package com.bookstore.filter;

import com.bookstore.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName OrderFilter
 * @Description 过滤所有请求事务处理流程
 * @Author Josen
 * @Date 2020/6/13 16:36
 * @Version 1.0
 **/
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // filterChain.doFilter：表示过滤器放行，程序继续执行
        try{
            filterChain.doFilter(servletRequest,servletResponse);
            // 提交事务并释放连接
            JdbcUtils.commitAndCloseConnection();
            System.out.println("****************提交事务************************");
        }catch (Exception e){
            // 事务回滚并释放连接
            JdbcUtils.rollbackAndCloseConnection();
            System.out.println("****************事务回滚************************");

            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
