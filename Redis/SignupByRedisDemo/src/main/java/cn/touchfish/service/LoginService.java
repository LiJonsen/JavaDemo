package cn.touchfish.service;
import cn.touchfish.beans.User;

import java.sql.SQLException;

/**
 * @InterfaceName LoginService
 * @Description Details
 * @Author Josen
 * @Create 2020/8/10 21:32
 */
public interface LoginService {
    /**
     * 校验注册用户信息
     * @param user
     * @return
     */
    String validateSignUpInfo(User user) throws SQLException;

    /**
     * 激活账号
     * @param username
     * @return
     */
    String activeAccount(String username) throws SQLException;

    /**
     * 校验用户名是否已存在
     * @param username
     * @return
     */
    boolean checkUsername(String username) throws SQLException;

    /**
     * 校验用户登录信息
     * @param username
     * @param password
     * @return
     */
    String validateSignIn(String username,String password) throws SQLException;

    /**
     * 校验用户是否已激活
     * @param username
     * @return
     */
    boolean checkUserIsActive(String username) throws SQLException;
}
