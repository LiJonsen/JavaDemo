package com.bookstore.service.user;

import com.bookstore.dao.user.UserDaoImpl;
import com.bookstore.pojo.User;

public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public User queryUsername(String username) {
        return userDao.queryUsername(username);
    }

    @Override
    public User queryUsernameAndPsd(User user) { 
        return userDao.queryUserAndPsd(user);
    }

    @Override
    public int registerAccount(User user) {
        return userDao.insertAccount(user);
    }
}
