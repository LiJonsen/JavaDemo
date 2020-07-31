package com.hello_spmvc.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName HelloWorldHandle
 * @Description 页面控制器/处理器
 * @Author Josen
 * @Create 2020/7/28 18:26
 */
@Controller
public class HelloWorldHandle {
    /**
     * 映射请求的名称：用于客户端请求；类似Struts2中action映射配置的action名称
     * 1. 使用 @RequestMapping 注解来映射请求的 URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于 InternalResourceViewResolver 视图解析器,
     * 会做如下的解析:
     *               通过 prefix + returnVal + suffix 这样的方式得到实际的物理视图, 然后做转发操作.
     *               /WEB-INF/views/success.jsp
     */
    @RequestMapping("hello")
    public String helloWorld(){
        System.out.println("run helloWorld handle...");

        return "success";
    }
}
