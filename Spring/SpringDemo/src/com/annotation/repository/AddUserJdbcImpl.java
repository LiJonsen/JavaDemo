package com.annotation.repository;

import org.springframework.stereotype.Repository;

/**
 * @ClassName AddUserJdbcImpl
 * @Description Repository持久化层
 * @Author Josen
 * @Create 2020/7/27 8:43
 */
@Repository
public class AddUserJdbcImpl implements AddUserJdbc{
    @Override
    public void addUserToDatabase() {
        System.out.println("Repository：添加用户到数据库...");
    }
}
