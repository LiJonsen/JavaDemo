package com.josen.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CheckGroupAndItemMapper
 * @Description CheckGroup 和 CheckItem中间关系表Mapper
 * @Author Josen
 * @Create 2020/9/6 18:35
 */
public interface CheckGroupAndItemMapper {
    /**
     * 根据groupId查询itemIds
     * @param id
     * @return
     */
    List<Integer> queryItemIdsByGroupId(@Param("id") Integer id);
    /**
     * 根据checkitemId 或 checkgroupId删除检查项
     * @param id
     * @param type false=根据checkitemId true=根据checkgroupId
     */
    void deleteByItemId(@Param("id") Integer id,@Param("type") boolean type);

    /**
     * 根据checkitem_id查询数量
     * @param id
     * @return
     */
    int queryCountByItemId(@Param("checkitemId") Integer id);

    /**
     * 新增检查项和检查组关联记录
     * @param groupId
     * @param itemId
     */
    void addRecord(@Param("groupId") Integer groupId, @Param("itemId") Integer itemId);
}
