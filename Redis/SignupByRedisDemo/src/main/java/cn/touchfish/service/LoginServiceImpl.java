package cn.touchfish.service;

import cn.touchfish.beans.Codes;
import cn.touchfish.beans.User;
import cn.touchfish.dao.LoginDao;
import cn.touchfish.dao.LoginDaoImpl;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.utils.MapperUtil;

import java.sql.SQLException;

/**
 * @ClassName LoginServiceImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/8/11 11:19
 */
public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao = new LoginDaoImpl();

    @Override
    public String validateSignUpInfo(User user) throws SQLException {
        // 用户已存在
        if(checkUsername(user.getUsername())){
            return Codes.CODE_1001;
        }

        // Old - dbUtils
//        int i = loginDao.insertOneUser(user);
        // New - Mybatis
        int i = MapperUtil.getSqlMapper(UserMapper.class).insertOneUser(user);
        MapperUtil.commitTransaction();
        MapperUtil.closeSqlSession();
        // 注册失败
        if (i > 0){
            return Codes.SUCCESS_CODE;
        }
        return Codes.CODE_1002;
    }
    @Override
    public String activeAccount(String username) throws SQLException {
        // 用户不存在-返回错误码
        if(!checkUsername(username)){
            return Codes.CODE_1003;
        }
        // 2. 更新is_active，激活账号
        // Old - dbUtils
//        int status = loginDao.updateUserActive(username);

        // New - Mybatis
        int status = MapperUtil.getSqlMapper(UserMapper.class).activeUser(username);
        MapperUtil.commitTransaction();
        MapperUtil.closeSqlSession();
        if (status <= 0) {
            return Codes.CODE_1004;
        }
        return Codes.SUCCESS_CODE;
    }

    @Override
    public boolean checkUsername(String username) throws SQLException {
//      Old - 使用dbUtils查询
//        User user = loginDao.queryOneUser(username);
        // New - 使用Mybatis查询
        User user = queryUserByName(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public String validateSignIn(String username, String password) throws SQLException {
        // 用户不存在
        if(!checkUsername(username)){
            return Codes.CODE_1003;
        }
        // 校验密码
        // Old - dbUtils
//        User user = loginDao.queryOneUser(username);
        // New - Mybatis
        User user = queryUserByName(username);
        if(user == null || !password.equals(user.getPassword())){
            return Codes.CODE_1006;
        }
        // 登录成功
        return Codes.SUCCESS_CODE;
    }

    @Override
    public boolean checkUserIsActive(String username) throws SQLException {
        // Old - dbUtils
//        User user = loginDao.queryOneUser(username);
        // New - Mybatis
        User user = queryUserByName(username);

        if(user!=null && user.getIs_active() == 1){
            return true;
        }
        return false;
    }

    /**
     * 根据username查询用户信息
     * 私有方法
     * @param username
     * @return
     */
    private User queryUserByName(String username){
        User query_user = new User();
        query_user.setUsername(username);
        User user = MapperUtil.getSqlMapper(UserMapper.class).queryUser(query_user);
        MapperUtil.closeSqlSession();
        return user;
    }
}
