package com.bookstore.test;

import com.bookstore.pojo.User;
import com.bookstore.service.user.UserServiceImpl;
import org.junit.Test;

public class TestRegister {
    @Test
    public void testing(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.queryUsername("admin");
        System.out.println(admin);
    }

}
