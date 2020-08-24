package cn.touchfish.mm.dao;

import cn.touchfish.wxApi.pojo.CategoryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CategoryMapper
 * @Description 面试题库Mapper
 * @Author Josen
 * @Create 2020/8/23 20:20
 */
public interface CategoryMapper {
    /**
     * 查询面试题库列表【按技术点查询】
     * @return
     */
    List<CategoryItem> queryCategoryItems(@Param("memberId") int memberId, @Param("courseId") int courseId);

    /**
     * 查询面试题库列表【按企业查询】
     * @param memberId
     * @return
     */
    List<CategoryItem> queryCategoryByCompany(@Param("memberId") int memberId);
    /**
     * 查询面试题库列表【按方向查询】
     * @param memberId
     * @return
     */
    List<CategoryItem> queryCategoryByIndustry(@Param("memberId") int memberId);
}
