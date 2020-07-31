package com.rest_crud.handle;

import com.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RequestDataHandle
 * @Description Details
 * @Author Josen
 * @Create 2020/7/29 15:32
 */
@Controller
public class RequestDataHandle {
    /**
      *         @RequestParam 注解用于映射请求参数
      *         value 用于映射请求参数名称
      *         required 用于设置请求参数是否必须的
      *         defaultValue 设置默认值，当没有传递参数时使用该值
     */
    @RequestMapping(value = "/testParams",method = RequestMethod.GET)
    public String handleParams(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "age",required = false,defaultValue = "0") int age
    ){
        System.out.println(username+","+age);
        return "success";
    }

    /**
     * @RequestHeader 获取请求头信息
     */
    @RequestMapping("/testHeader")
    public String handleReqHeader(@RequestHeader(value = "Accept-Language") String al){
        System.out.println(al);
        return "success";
    }

    /**
     * 以POJO形式接收参数
     * 获取原生Servlet request & response对象
     */
    @RequestMapping(value = "/testPojo",method = RequestMethod.POST)
    public void handleReqToPOJO(User user, HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println(user);
        res.getWriter().println(user);
    }

    @RequestMapping(value = "/testRedirect",method = RequestMethod.GET)
    public String handleRedirect(){
        return "redirect:/test.html";
    }


}
