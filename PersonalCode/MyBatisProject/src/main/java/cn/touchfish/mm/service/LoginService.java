package cn.touchfish.mm.service;

import cn.touchfish.mm.dao.PermissionMapper;
import cn.touchfish.mm.dao.RoleMapper;
import cn.touchfish.mm.dao.UserMapper;
import cn.touchfish.mm.pojo.Permission;
import cn.touchfish.mm.pojo.Role;
import cn.touchfish.mm.pojo.User;
import cn.touchfish.mm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoginService
 * @Description
 * @Author Josen
 * @Create 2020/8/18 11:14
 */
public class LoginService {
    public User validateSignIn(User user) throws IOException {
        // 校验登录
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User db_user = mapper.queryOneUser(user);
        MybatisUtils.commitAndClose(sqlSession);

        if(db_user!=null){
            // 获取用户权限相关数据
            List<String> permissions = getUserPermission(db_user);
            db_user.setAuthorityList(permissions);
            return db_user;
        }

        return null;
    }

    /**
     * 获取用户权限相关数据
     * @param user
     * @return 用户拥有的权限关键字
     */
    private List<String> getUserPermission(User user) throws IOException {
        // 存储该用户的所有权限关键字
        List<String> permissionList = new ArrayList<>();

        SqlSession sqlSession = MybatisUtils.openSqlSession();

        // 1. 根据用户userId，获取拥有的角色
        List<Role> roles = sqlSession.getMapper(RoleMapper.class).getRolesByUid(user.getId());
        if(roles!=null && roles.size()>0){
            // 2. 根据角色roleId，获取所有拥有的操作权限
            for (Role role : roles) {
                permissionList.add(role.getKeyword());
                List<Permission> permissions = sqlSession.getMapper(PermissionMapper.class).getPermissionsByRid(role.getId());
                if(permissions!=null && permissions.size()>0){
                    for (Permission permission : permissions) {
                        permissionList.add(permission.getKeyword());
                    }
                }
            }
        }
        MybatisUtils.commitAndClose(sqlSession);
        return permissionList;
    }
}
