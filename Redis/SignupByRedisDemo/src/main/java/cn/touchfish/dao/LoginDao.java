package cn.touchfish.dao;

import cn.touchfish.beans.SignUpAccount;

import java.sql.SQLException;

/**
 * @ClassName LoginDao
 * @Description 登录/注册Dao
 * @Author Josen
 * @Create 2020/8/10 21:32
 */
public interface LoginDao {
    /**
     * 向t_user表插入一条用户记录
     * @param account
     * @return
     */
    int insertOneUser(SignUpAccount account) throws SQLException;

    /**
     * 查询一条用户记录
     * @param account
     * @return
     */
    SignUpAccount queryOneUser(String account) throws SQLException;

    /**
     * 更新用户激活状态
     * @param account
     * @return
     */
    int updateUserActive(String account) throws SQLException;
}
