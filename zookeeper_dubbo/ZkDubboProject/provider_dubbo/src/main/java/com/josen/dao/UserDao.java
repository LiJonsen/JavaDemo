package com.josen.dao;

import com.josen.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserDao
 * @Description
 * @Author Josen
 * @Create 2020/9/4 11:10
 */
public interface UserDao {
    User queryUserById(@Param("id") Integer id);
}
