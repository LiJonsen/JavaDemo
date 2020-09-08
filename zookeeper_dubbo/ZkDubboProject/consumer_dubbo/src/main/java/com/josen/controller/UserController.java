package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.pojo.User;
import com.josen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description User服务消费者-调用provider中的服务
 * RestController = @Controller+@ResponseBody
 * @Author Josen
 * @Create 2020/9/4 12:11
 */
@Controller
@RequestMapping("/test")
public class UserController {
    @Reference
    private UserService userService;

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User queryUser(Integer id){
        User user = userService.queryUserById(id);
        System.out.println("id="+id);
        System.out.println("user="+user);
        return user;
    }
}
