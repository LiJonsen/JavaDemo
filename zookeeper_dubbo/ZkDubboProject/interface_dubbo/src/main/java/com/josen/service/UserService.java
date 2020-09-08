package com.josen.service;

import com.josen.pojo.User;

/**
 * @ClassName UserService
 * @Description
 * @Author Josen
 * @Create 2020/9/3 14:23
 */
public interface UserService {
    User queryUserById(Integer id);
}
