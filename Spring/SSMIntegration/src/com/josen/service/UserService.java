package com.josen.service;

import com.josen.beans.User;
import com.josen.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description 业务逻辑层
 * @Author Josen
 * @Create 2020/7/31 12:43
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 处理添加用户业务逻辑
     * @param user
     */
    public void addUserLogic(User user){
        System.out.printf("Service：处理添加 name=%s 业务逻辑...\n",user.getName());
        userDao.addUserToDatabase(user);
    }

}
