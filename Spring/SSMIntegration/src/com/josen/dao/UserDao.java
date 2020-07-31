package com.josen.dao;

import com.josen.beans.User;
import com.josen.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Description 持久化层
 * @Author Josen
 * @Create 2020/7/31 12:43
 */
@Repository
public class UserDao {
// 报错：在子容器mvc中时，是无法访问到父容器的bean的
    //    @Autowired
//    private UserHandler userHandler;
    /**
     * 添加用户信息到数据库
     * @param user
     */
    public void addUserToDatabase(User user){
//        System.out.println(userHandler);
        System.out.printf("Dao：添加 name=%s 到数据库...\n",user.getName());
    }
}
