package com.annotation.controller;

import com.annotation.service.AddUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName AddUser
 * @Description 表述层/控制层
 * @Author Josen
 * @Create 2020/7/27 8:37
 */
@Controller
public class AddUser {
    @Autowired
    private AddUserServiceImpl addUserService;

    public void runAddUser(){
        System.out.println("Controller：调用添加用户业务逻辑");
        addUserService.excuteAddUser();
    }
}
