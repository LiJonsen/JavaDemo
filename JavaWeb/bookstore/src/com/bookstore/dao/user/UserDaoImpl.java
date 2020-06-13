package com.bookstore.dao.user;
import com.bookstore.dao.BaseDao;
import com.bookstore.pojo.User;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User queryUsername(String username){
        String sql = "select * from store_user where username=?";
        return queryForBean(sql,username);
    }

    @Override
    public User queryUserAndPsd(User user){
        String sql = "select * from store_user where username=? and password=?";
        return queryForBean(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public int insertAccount(User user){
        String sql = "insert into store_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
