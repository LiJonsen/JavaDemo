package com.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName InterceptorHandler
 * @Description Details
 * @Author Josen
 * @Create 2020/7/31 11:40
 */
@Controller
public class InterceptorHandler {
    @RequestMapping(value = "testInterceptor")
    public String handlerInterceptorRequest(){
        System.out.println("接收到testInterceptor请求...");
        return "success";
    }
}
