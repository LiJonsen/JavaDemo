package com.josen.mapper;

import com.josen.pojo.CheckGroup;
import com.josen.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CheckGroupMapper
 * @Description 检查组Mapper
 * @Author Josen
 * @Create 2020/9/6 20:40
 */
public interface CheckGroupMapper {
    /**
     * 根据套餐id查询检查组列表
     */
    List<CheckGroup> queryGroupBySetmealId(@Param("id") Integer id);
    /**
     * 分页查询检查组列表
     * @param queryString 编码/名称/助记码
     * @return
     */
    List<CheckItem> queryListByPage(@Param("keyword") String queryString);

    /**
     * 新增检查组
     * @param checkGroup
     */
    void addGroup(CheckGroup checkGroup);

    /**
     * 更新检查组
     * @param checkGroup
     */
    void updateGroup(CheckGroup checkGroup);

    /**
     * 删除检查组
     * @param id
     */
    void deleteGroupById(Integer id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> queryAllGroup();
}
