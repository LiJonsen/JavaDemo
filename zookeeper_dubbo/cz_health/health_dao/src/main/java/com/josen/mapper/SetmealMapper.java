package com.josen.mapper;

import com.josen.pojo.CheckItem;
import com.josen.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName SetmealMapper
 * @Description 套餐Dao接口
 * @Author Josen
 * @Create 2020/9/7 18:09
 */
public interface SetmealMapper {
    /**
     * 分页查询检查组列表
     * @param queryString 编码/名称/助记码
     * @return
     */
    List<CheckItem> queryListByPage(@Param("keyword") String queryString);

    /**
     * 新增套餐
     * @param setmeal
     */
    void addSetmeal(Setmeal setmeal);

    /**
     * 更新套餐数据
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * 根据id删除套餐记录
     * @param id
     */
    void deleteSetmeal(@Param("id") Integer id);
}
