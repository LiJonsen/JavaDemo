package com.josen.mapper;

import com.josen.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CheckItemMapper
 * @Description 检查项管理表操作
 * @Author Josen
 * @Create 2020/9/6 15:31
 */
public interface CheckItemMapper {
    /**
     * 分页查询检查项列表
     * @return
     */
    List<CheckItem> queryListByPage(@Param("keyword") String keyword);

    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> queryAllItem();

    /**
     * 新增检查项
     * @param checkItem
     */
    void addCheckItem(CheckItem checkItem);

    /**
     * 编辑检查项
     * @param checkItem
     */
    void editCheckItem(CheckItem checkItem);

    /**
     * 删除检查项
     * @param id
     */
    void deleteCheckItemById(@Param("id") Integer id);
}
