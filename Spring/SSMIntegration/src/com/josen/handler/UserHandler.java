package com.josen.handler;

import com.josen.beans.User;
import com.josen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserHandler
 * @Description 控制/处理层
 * @Author Josen
 * @Create 2020/7/31 12:41
 */
@Controller
public class UserHandler {
    @Autowired
    private UserService userService;
    @RequestMapping("/addUser")
    public String handlerAddUser(
            @RequestParam("name") String name,
            @RequestParam("skill") String skill,
            HttpSession session
    ){
        System.out.printf("Handler/Controller：接受到请求，处理添加 name=%s,skill=%s...\n",name,skill);
        userService.addUserLogic(new User(name,skill));
        // 获取Spring IOC容器对象
        ServletContext sc = session.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
        User user = context.getBean("user", User.class);
        System.out.println("Spring IOC容器获取："+user);
        return "success";
    }
}
