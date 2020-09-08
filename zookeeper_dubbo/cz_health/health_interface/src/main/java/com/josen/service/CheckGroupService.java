package com.josen.service;

import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.pojo.CheckGroup;
import com.josen.pojo.CheckItem;

import java.util.List;

/**
 * @InterfaceName CheckGroupService
 * @Description
 * @Author Josen
 * @Create 2020/9/6 20:38
 */
public interface CheckGroupService {
    /**
     * 分页查询检查组列表
     * @param pageBean
     * @return
     */
    PageResult queryListByPage(QueryPageBean pageBean);

    /**
     * 添加检查组业务逻辑
     * @param checkGroup
     */
    void addCheckGroup(CheckGroup checkGroup);

    /**
     * 编辑检查组业务逻辑
     * @param checkGroup
     */
    void editCheckGroup(CheckGroup checkGroup);

    /**
     * 删除检查组
     * @param id
     */
    void deleteCheckGroup(Integer id);

    /**
     * 获取所有检查组
     * @return
     */
    List<CheckGroup> queryAllGroup();
}
