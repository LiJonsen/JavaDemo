package cn.touchfish.service;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.dao.UserDao;
import cn.touchfish.dao.UserDaoImpl;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.utils.MapperUtil;
import java.util.List;

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
        return userList;

        // Old - 使用dbUtils
//        return userDao.queryUserList();
    }
    // 分页查询用户信息
    @Override
    public List<HomeUser> getUserListByPage(int current, int pageSize) {
        // 分页算法
        int num = (current-1)*current;
        List<HomeUser> userList = MapperUtil.getSqlMapper(UserMapper.class).queryUserList(num,pageSize);
        return userList;
    }

}
