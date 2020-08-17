package cn.touchfish.mapper;

import cn.touchfish.beans.CountForUser;
import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName UserMapper
 * @Description t_user表接口映射
 * @Author Josen
 * @Create 2020/8/14 20:23
 */
public interface UserMapper {
    // 插入一条用户记录
    int insertOneUser(User user);
    // 查询首页用户列表
    List<HomeUser> queryUserList();
    // 分页查询用户列表
    List<HomeUser> queryUserList(@Param("current") int current, @Param("pageSize") int pageSize);

    // 查询一条用户记录
    User queryUser(User user);
    // 激活用户
    int activeUser(String username);

    List<CountForUser> queryUserForCount();

}
