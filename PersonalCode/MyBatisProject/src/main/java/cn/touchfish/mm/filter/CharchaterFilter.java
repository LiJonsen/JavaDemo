package cn.touchfish.mm.filter;

import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws ServletException, IOException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");
        try {
            filterChain.doFilter(request,response);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(500,"服务器响应异常！"));
        }

    }

    @Override
    public void destroy() {

    }
}
