package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.josen.entity.*;
import com.josen.mapper.SetmealAndGroupMapper;
import com.josen.mapper.SetmealMapper;
import com.josen.pojo.CheckItem;
import com.josen.pojo.Setmeal;
import com.josen.service.SetmealService;
import com.josen.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.List;

/**
 * @ClassName SetmealServiceImpl
 * @Description 套餐接口业务逻辑
 * @Author Josen
 * @Create 2020/9/7 18:07
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealAndGroupMapper setmealAndGroupMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public PageResult queryListByPage(QueryPageBean pageBean) {
        Jedis resource = jedisPool.getResource();
        System.out.println("resource=" + resource);
        // 使用PageHelper插件实现分页查询
        // 1-完成对分页初始化工作
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        // 2-执行查询语句
        List<CheckItem> checkItems = setmealMapper.queryListByPage(pageBean.getQueryString());
        // 3-PageHelper会根据查询的结果再封装成PageHealper对应的实体类
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItems);
        // 4-返回结果
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void addSetmeal(Setmeal setmeal) {
        // 1. 新增检查组
        setmealMapper.addSetmeal(setmeal);

        // 2. 新增当前检查组下的检查项
        if (setmeal.getCheckGroupIds().size() > 0) {
            this.executionAddCheckGroup(setmeal);
        }

    }

    @Override
    public void editSetmeal(Setmeal setmeal) {

        // 删除当前套餐所有检查组
        setmealAndGroupMapper.deleteById(setmeal.getId(), true);

        // 更新套餐数据
        setmealMapper.updateSetmeal(setmeal);

        // 更新当前套餐最新的所有检查组
        executionAddCheckGroup(setmeal);
    }

    @Override
    public void deleteSetmeal(Integer id) {
        // 判断该套餐是否存在
        Setmeal setmeal = setmealMapper.querySetmealById(id);
        if(setmeal!=null){
            // 1.根据套餐id删除检查组
            setmealAndGroupMapper.deleteById(id, true);
            // 2.删除检查套餐
            setmealMapper.deleteSetmeal(id);
            // 3. 删除七牛云中的图片
            if(setmeal.getImg()!=null && !"".equals(setmeal.getImg())){
                QiNiuUtil.deleteImg(setmeal.getImg());
            }
        }
    }

    @Override
    public Result uploadImage(byte[] bytes, String name) {
        Result result = new Result(false, MessageConstant.REQ_PARAM_ERROR, "");
        String message = QiNiuUtil.uploadImage(bytes, name);
        result.setMessage(message);
        if (!MessageConstant.PIC_UPLOAD_FORMAT_ERROR.equals(message) && !MessageConstant.PIC_UPLOAD_FAIL.equals(message)) {
            result.setMessage(MessageConstant.PIC_UPLOAD_SUCCESS);
            result.setFlag(true);
            result.setData(message);

            // 上传成功-（用于清理垃圾图片）
//            Jedis resource = jedisPool.getResource();
//            resource.sadd(RedisConstant.SETMEAL_PIC_RESOURCES, message);
//            resource.close();
        }
        return result;
    }

    @Override
    public List<Setmeal> queryAllSetmeal() {
        return setmealMapper.querySetmeal(null);
    }

    @Override
    public Setmeal queryDetails(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.querySetmeal(setmeal);

        Setmeal detail = list.get(0);
        return detail;
    }

    /**
     * 在关联表中添加检查组记录
     */
    private void executionAddCheckGroup(Setmeal setmeal) {
        List<Integer> ids = setmeal.getCheckGroupIds();
        for (Integer gid : ids) {
            setmealAndGroupMapper.addRecord(setmeal.getId(), gid);
        }
    }
}
