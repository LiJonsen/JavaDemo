package cn.touchfish.mm.dao;
import cn.touchfish.mm.pojo.User;

/**
 * @ClassName UserMapper
 * @Description 用户管理
 * @Author Josen
 * @Create 2020/8/18 11:14
 */
public interface UserMapper {
    User queryOneUser(User user);
}
