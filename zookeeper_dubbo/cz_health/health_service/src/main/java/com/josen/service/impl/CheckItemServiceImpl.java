package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.mapper.CheckGroupAndItemMapper;
import com.josen.mapper.CheckItemMapper;
import com.josen.pojo.CheckItem;
import com.josen.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CheckItemServiceImpl
 * @Description 检查项业务逻辑
 * @Author Josen
 * @Create 2020/9/6 15:31
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;
    @Autowired
    private CheckGroupAndItemMapper checkGroupAndItemMapper;

    @Override
    public PageResult queryListByPage(QueryPageBean queryPageBean) {
        // 使用PageHelper插件实现分页查询
        // 1-完成对分页初始化工作
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 2-执行查询语句
        List<CheckItem> checkItems = checkItemMapper.queryListByPage(queryPageBean.getQueryString());
        // 3-PageHelper会根据查询的结果再封装成PageHealper对应的实体类
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItems);
        // 4-返回结果
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<CheckItem> queryAllItem() {
        return checkItemMapper.queryAllItem();
    }

    @Override
    public void addCheckItem(CheckItem checkItem) {
        checkItemMapper.addCheckItem(checkItem);
    }

    @Override
    public void editCheckItem(CheckItem checkItem) {
        checkItemMapper.editCheckItem(checkItem);
    }

    @Override
    public void deleteCheckItem(Integer id) {
        //1.判断该检查项是否拥有检查组
        int item_id = checkGroupAndItemMapper.queryCountByItemId(id);
        if (item_id > 0) {
            // 2.有检查组，删除在关联表中的记录
            checkGroupAndItemMapper.deleteByItemId(id,false);
        }
        // 3.删除检查项
        checkItemMapper.deleteCheckItemById(id);
    }
}
