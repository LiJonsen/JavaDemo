package cn.touchfish.dao;

import cn.touchfish.beans.SignUpAccount;
import java.sql.SQLException;

/**
 * @ClassName LoginDaoImpl
 * @Description
 * @Author Josen
 * @Create 2020/8/11 11:21
 */
public class LoginDaoImpl extends BaseDao<SignUpAccount> implements LoginDao{
    @Override
    public int insertOneUser(SignUpAccount account) throws SQLException {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int update = update(sql, account.getUsername(), account.getPassword(), account.getEmail());
        return update;
    }

    @Override
    public SignUpAccount queryOneUser(String account) throws SQLException {
        String sql = "select * from t_user where username=?";
        return queryOne(sql,account);
    }

    @Override
    public int updateUserActive(String account) throws SQLException {
        String sql = "update t_user set is_active=1 where username=?";
        return update(sql,account);
    }
}
