package com.bookstore.service.user;

import com.bookstore.pojo.User;

import java.sql.SQLException;

/**
 * @ClassName UserService
 * @Description 为Web层提供操作UserDAO的方法
 * @Author Josen
 * @Date 2020/6/10 13:48
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User queryUsername(String username);

    /**
     * 根据用户名和密码查询
     * @param user
     * @return
     */
    User queryUsernameAndPsd(User user);

    /**
     * 注册功能
     * @param user
     * @return
     */
    int registerAccount(User user);
}
