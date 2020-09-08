package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.mapper.CheckGroupAndItemMapper;
import com.josen.mapper.CheckGroupMapper;
import com.josen.pojo.CheckGroup;
import com.josen.pojo.CheckItem;
import com.josen.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CheckGroupServiceImpl
 * @Description 检查组业务逻辑
 * @Author Josen
 * @Create 2020/9/6 20:39
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupMapper checkGroupMapper;
    @Autowired
    private CheckGroupAndItemMapper checkGroupAndItemMapper;

    @Override
    public PageResult queryListByPage(QueryPageBean pageBean) {
        // 使用PageHelper插件实现分页查询
        // 1-完成对分页初始化工作
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        // 2-执行查询语句
        List<CheckItem> checkItems = checkGroupMapper.queryListByPage(pageBean.getQueryString());
        // 3-PageHelper会根据查询的结果再封装成PageHealper对应的实体类
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItems);
        // 4-返回结果
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void addCheckGroup(CheckGroup checkGroup) {
        // 1. 新增检查组
        checkGroupMapper.addGroup(checkGroup);

        // 2. 新增当前检查组下的检查项
        if(checkGroup.getCheckItemIds().size()>0){
            executionAddCheckItems(checkGroup);
        }

    }

    @Override
    public void editCheckGroup(CheckGroup checkGroup) {
        // 删除当前组所有检查项
        checkGroupAndItemMapper.deleteByItemId(checkGroup.getId(), true);

        // 更新检查组
        checkGroupMapper.updateGroup(checkGroup);

        // 更新当前最新的所有检查项
        executionAddCheckItems(checkGroup);
    }

    @Override
    public void deleteCheckGroup(Integer id) {
        // 1.根据检查组id删除检查项
        checkGroupAndItemMapper.deleteByItemId(id, true);
        // 2.删除检查组
        checkGroupMapper.deleteGroupById(id);
    }

    @Override
    public List<CheckGroup> queryAllGroup() {
        return checkGroupMapper.queryAllGroup();
    }

    /**
     * 在关联表中添加检查项
     */
    private void executionAddCheckItems(CheckGroup checkGroup) {
        List<Integer> ids = checkGroup.getCheckItemIds();
        for (Integer itemId : ids) {
            checkGroupAndItemMapper.addRecord(checkGroup.getId(), itemId);
        }
    }
}
