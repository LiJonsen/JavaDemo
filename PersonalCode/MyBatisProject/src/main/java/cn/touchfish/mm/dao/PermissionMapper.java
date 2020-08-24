package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.Permission;

import java.util.List;

/**
 * @InterfaceName PermissionMapper
 * @Description 权限Mapper
 * @Author Josen
 * @Create 2020/8/22 16:02
 */
public interface PermissionMapper {
    /**
     * 根据角色roleId查询权限信息
     */
    List<Permission> getPermissionsByRid(int rid);
}
