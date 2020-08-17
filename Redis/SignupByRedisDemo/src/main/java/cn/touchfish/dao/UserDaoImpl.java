package cn.touchfish.dao;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description
 * @Author Josen
 * @Create 2020/8/13 17:35
 */
public class UserDaoImpl extends BaseDao<HomeUser> implements UserDao{
    @Override
    public List<HomeUser> queryUserList() throws SQLException {
        String sql = "select username,email,is_active from t_user";
        return queryList(sql);
    }
}
