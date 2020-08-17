package cn.touchfish.dao;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @InterfaceName UserDao
 * @Description 用户相关Dao操作
 * @Author Josen
 * @Create 2020/8/13 17:33
 */
public interface UserDao {
    List<HomeUser> queryUserList() throws SQLException;
}
