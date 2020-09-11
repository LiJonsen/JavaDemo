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

    /**
     * 根据id查询套餐记录
     */
    Setmeal querySetmealById(@Param("id") Integer id);

    /**
     * 查询所有套餐列表（包含检查组、检查项信息）
     * @return
     */
    List<Setmeal> querySetmeal(Setmeal setmeal);
}
