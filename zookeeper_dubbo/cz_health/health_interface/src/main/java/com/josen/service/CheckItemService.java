package com.josen.service;

import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.pojo.CheckItem;

import java.util.List;

/**
 * @InterfaceName CheckItemService
 * @Description 检查项管理业务逻辑
 * @Author Josen
 * @Create 2020/9/6 15:29
 */
public interface CheckItemService {
    /**
     * 分页查询检查项列表
     * @return
     */
    PageResult queryListByPage(QueryPageBean queryPageBean);

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
    void deleteCheckItem(Integer id);
}
