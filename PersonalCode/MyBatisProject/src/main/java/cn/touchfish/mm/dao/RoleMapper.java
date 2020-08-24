package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.Role;

import java.util.List;

/**
 * @InterfaceName RoleMapper
 * @Description 角色Mapper
 * @Author Josen
 * @Create 2020/8/22 15:54
 */
public interface RoleMapper {
    /**
     * 根据用户userId查询角色信息
     */
    List<Role> getRolesByUid(int uid);
}
