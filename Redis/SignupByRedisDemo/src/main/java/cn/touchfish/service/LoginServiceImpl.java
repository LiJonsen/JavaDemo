package cn.touchfish.service;

import cn.touchfish.beans.Codes;
import cn.touchfish.beans.SignUpAccount;
import cn.touchfish.dao.LoginDao;
import cn.touchfish.dao.LoginDaoImpl;

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
    public String validateSignUpInfo(SignUpAccount account) throws SQLException {
        // 用户已存在
        if(checkUsername(account.getUsername())){
            return Codes.CODE_1001;
        }

        int i = loginDao.insertOneUser(account);
        // 注册失败
        if (i <= 0){
            return Codes.CODE_1002;
        }
        return Codes.SUCCESS_CODE;
    }
    @Override
    public String activeAccount(String account) throws SQLException {
        // 用户不存在-返回错误码
        if(!checkUsername(account)){
            return Codes.CODE_1003;
        }
        // 2. 更新is_active，激活账号
        int status = loginDao.updateUserActive(account);
        if (status <= 0) {
            return Codes.CODE_1004;
        }
        return Codes.SUCCESS_CODE;
    }

    @Override
    public boolean checkUsername(String username) throws SQLException {
        SignUpAccount user = loginDao.queryOneUser(username);
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
        SignUpAccount user = loginDao.queryOneUser(username);
        if(user == null || !password.equals(user.getPassword())){
            return Codes.CODE_1006;
        }
        // 登录成功
        return Codes.SUCCESS_CODE;
    }

    @Override
    public boolean checkUserIsActive(String username) throws SQLException {
        SignUpAccount user = loginDao.queryOneUser(username);
        if(user!=null && user.getIs_active() == 1){
            return true;
        }
        return false;
    }
}
