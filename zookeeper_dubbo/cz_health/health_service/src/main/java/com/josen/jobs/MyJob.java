package com.josen.jobs;

import com.josen.entity.RedisConstant;
import com.josen.mapper.SetmealMapper;
import com.josen.pojo.Setmeal;
import com.josen.utils.DateUtils;
import com.josen.utils.QiNiuUtil;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MyJob
 * @Description 任务调度案例
 * @Author Josen
 * @Create 2020/9/9 14:02
 */
public class MyJob {
    @Autowired
    private SetmealMapper setmealMapper;
//    @Autowired
//    private JedisPool jedisPool;

    public void run() {
        System.out.println("job execute...,执行时间：" + new Date());
    }

    /**
     * 定时清理七牛云无用图片任务
     */
    public void cleanUselessImgJob() {
        // 1. 获取数据库中真实存在的图片
        List<Setmeal> setmeals = setmealMapper.querySetmeal(null);
        List<String> db_list = new ArrayList<>();
        for (Setmeal setmeal : setmeals) {
            String img = setmeal.getImg();
            if(img!=null && !"".equals(img)){
                db_list.add(img);
            }
        }

        // 2. 获取七牛云上存储的图片
        List<String> qn_list = QiNiuUtil.getBatchQiNiuImg();

        // 3. 删除数据库中不存在的七牛云图片
        for (String item : qn_list) {
            // 4. 判断当前七牛云图片是否存在数据库
            if(!db_list.contains(item)){
                // 5. 不存在-删除该七牛云图片
                QiNiuUtil.deleteImg(item);
            }
        }
        System.out.println("db_list=>"+db_list);
        System.out.println("qn_list=>"+qn_list);
        System.out.println(" ======> cleanUselessImgJob 清理完成..."+ DateUtils.parseDateTime2String(new Date()));
    }
}
