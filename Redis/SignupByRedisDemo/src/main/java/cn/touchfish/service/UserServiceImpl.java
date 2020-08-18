package cn.touchfish.service;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.dao.UserDao;
import cn.touchfish.dao.UserDaoImpl;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.utils.MapperUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author Josen
 * @Create 2020/8/13 17:41
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    // 查询所有用户信息
    @Override
    public List<HomeUser> getUserList() {
        // 使用Mybatis
        List<HomeUser> userList = MapperUtil.getSqlMapper(UserMapper.class).queryUserList();
        MapperUtil.closeSqlSession();
        return userList;
        // Old - 使用dbUtils
//        return userDao.queryUserList();
    }
    // 分页查询用户信息
    @Override
    public List<HomeUser> getUserListByPage(int current, int pageSize) {
        int num = (current-1)*pageSize;
        List<HomeUser> userList = MapperUtil.getSqlMapper(UserMapper.class).queryUserList(num, pageSize);
        MapperUtil.closeSqlSession();
        return userList;
    }

}
