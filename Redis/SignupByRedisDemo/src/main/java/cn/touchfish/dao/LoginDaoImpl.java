package cn.touchfish.dao;

import cn.touchfish.beans.User;
import java.sql.SQLException;

/**
 * @ClassName LoginDaoImpl
 * @Description
 * @Author Josen
 * @Create 2020/8/11 11:21
 */
public class LoginDaoImpl extends BaseDao<User> implements LoginDao{
    @Override
    public int insertOneUser(User account) throws SQLException {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int update = update(sql, account.getUsername(), account.getPassword(), account.getEmail());
        return update;
    }

    @Override
    public User queryOneUser(String account) throws SQLException {
        String sql = "select * from t_user where username=?";
        return queryOne(sql,account);
    }

    @Override
    public int updateUserActive(String account) throws SQLException {
        String sql = "update t_user set is_active=1 where username=?";
        return update(sql,account);
    }
}
