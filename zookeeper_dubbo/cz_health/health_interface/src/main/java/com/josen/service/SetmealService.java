package com.josen.service;

import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.pojo.Setmeal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @InterfaceName SetmealService
 * @Description Details
 * @Author Josen
 * @Create 2020/9/7 18:07
 */
public interface SetmealService {
    /**
     * 分页查询套餐列表
     * @param pageBean
     * @return
     */
    PageResult queryListByPage(QueryPageBean pageBean);

    /**
     * 新增套餐
     * @param setmeal
     */
    void addSetmeal(Setmeal setmeal);

    /**
     * 编辑套餐
     * @param setmeal
     */
    void editSetmeal(Setmeal setmeal);

    /**
     * 删除套餐
     * @param id
     */
    void deleteSetmeal(Integer id);

}
