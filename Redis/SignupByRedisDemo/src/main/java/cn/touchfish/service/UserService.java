package cn.touchfish.service;

import cn.touchfish.beans.HomeUser;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserService
 * @Description 获取用户列表业务逻辑
 * @Author Josen
 * @Create 2020/8/13 17:40
 */
public interface UserService {
    List<HomeUser> getUserList() ;
    List<HomeUser> getUserListByPage(int current,int pageSize);
}
