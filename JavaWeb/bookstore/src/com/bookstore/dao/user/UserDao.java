package com.bookstore.dao.user;
import com.bookstore.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName BaseDao
 * @Description 定义操作store_user数据表的方法封装
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 1. 校验用户名是否已存在
     */
    User queryUsername(String username);

    /**
     * 2. 根据用户名和密码查询用户是否存在
     */
    User queryUserAndPsd(User user);

    /**
     * 3. 向store_user表添加一条记录-注册功能
     */
    int insertAccount(User user);
}
