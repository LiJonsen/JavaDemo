package com.josen.service;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.entity.Result;
import com.josen.pojo.Setmeal;
import java.util.List;

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

    /**
     * 上传图片到七牛云
     * @return
     */
    Result uploadImage(byte[] bytes,String name);

    /**
     * 查询所有套餐列表
     * @return
     */
    List<Setmeal> queryAllSetmeal();

    /**
     * 查询套餐详情（包含检查组、检查项信息）
     * @param setmeal
     * @return
     */
    Setmeal queryDetails(Setmeal setmeal);
}
