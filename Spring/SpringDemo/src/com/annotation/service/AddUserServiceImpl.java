package com.annotation.service;

import com.annotation.repository.AddUserJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AddUserServiceImpl
 * @Description Service业务逻辑层
 * @Author Josen
 * @Create 2020/7/27 8:42
 */
@Service
public class AddUserServiceImpl implements AddUserService{
    @Autowired
    private AddUserJdbcImpl addUserJdbc;
    @Override
    public boolean excuteAddUser() {
        System.out.println("Service：调用添加用户JDBC方法...");

        addUserJdbc.addUserToDatabase();
        return false;
    }
}
