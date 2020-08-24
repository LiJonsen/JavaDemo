package cn.touchfish.mm.dao;

import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CourseMapper
 * @Description 学科管理
 * @Author Josen
 * @Create 2020/8/18 13:40
 */
public interface CourseMapper {
    /**
     * 学科管理列表分页查询
     * QueryPageBean=查询条件bean
     * @return
     */
    List<Course> queryCourseListByPage(QueryPageBean queryPageBean);

    /**
     * 查询学科管理列表总记录数
     * @return
     */
    long queryCourseListTotal(QueryPageBean qpb);

    /**
     * 添加一条学科记录
     * @param course
     */
    boolean insertOneCourse(Course course);

    /**
     * 修改一条学科记录
     * @param course
     * @return
     */
    boolean updateOneCourse(Course course);

    /**
     * 删除一条学科记录
     * @param id
     * @return
     */
    boolean deleteOneCourse(@Param("courseId") int id);

    /**
     * 只查询id & name字段的学科列表
     * @param status 前台是否显示
     * @return
     */
    List<Course> querySimpleList(@Param("is_show") int status);
}
