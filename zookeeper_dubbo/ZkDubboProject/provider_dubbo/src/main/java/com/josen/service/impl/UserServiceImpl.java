package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.josen.dao.UserDao;
import com.josen.pojo.User;
import com.josen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserServiceImpl
 * @Description User服务提供者
 * @Author Josen
 * @Create 2020/9/4 9:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }
}
