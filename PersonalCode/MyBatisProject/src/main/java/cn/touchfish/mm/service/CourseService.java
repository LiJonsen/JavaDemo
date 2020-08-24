package cn.touchfish.mm.service;

import cn.touchfish.mm.dao.CourseMapper;
import cn.touchfish.mm.entity.PageResult;
import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.pojo.Course;
import cn.touchfish.mm.utils.DateUtils;
import cn.touchfish.mm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.*;

/**
 * @ClassName CourseService
 * @Description 学科管理Service
 * @Author Josen
 * @Create 2020/8/18 15:00
 */
public class CourseService {
    /**
     * 获取学科管理列表
     * @param qpb
     * @return
     * @throws IOException
     */
    public PageResult getCourseListByPage(QueryPageBean qpb) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        // 计算分页
        int current = qpb.getCurrentPage();
        qpb.setOffset((current-1) * qpb.getPageSize());
        // 模糊查询name拼接
        Map queryParams = qpb.getQueryParams();
        String name = (String)queryParams.get("name");
        if(name!=null && !"".equals(name)){
            queryParams.put("name","%"+queryParams.get("name")+"%");
            qpb.setQueryParams(queryParams);
        }

        // 查询学科列表
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        List<Course> list = mapper.queryCourseListByPage(qpb);
        long total = mapper.queryCourseListTotal(qpb);
        MybatisUtils.commitAndClose(sqlSession);
        return new PageResult(total,list);
    }

    /**
     * 添加学科
     * @param course
     */
    public boolean addCourse(Course course) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        String createDate = DateUtils.parseDate2String(new Date());
        course.setCreateDate(createDate);
        boolean status = sqlSession.getMapper(CourseMapper.class).insertOneCourse(course);
        MybatisUtils.commitAndClose(sqlSession);
        return status;
    }

    /**
     * 修改一条学科信息
     * @param course
     * @return
     */
    public boolean modifyCourse(Course course) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        boolean status = sqlSession.getMapper(CourseMapper.class).updateOneCourse(course);

        MybatisUtils.commitAndClose(sqlSession);
        return status;
    }

    /**
     * 根据id删除学科
     * @param courseId
     * @return
     * @throws IOException
     */
    public boolean deleteCourse(int courseId) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        boolean status = sqlSession.getMapper(CourseMapper.class).deleteOneCourse(courseId);
        MybatisUtils.commitAndClose(sqlSession);
        return status;
    }

    /**
     * 延迟加载学科列表
     */
    public List<Course> getSimpleList(Course course) throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        List<Course> list = sqlSession.getMapper(CourseMapper.class).querySimpleList(course.getIsShow());
        MybatisUtils.commitAndClose(sqlSession);

        // 只返回id & name属性的学科列表
        Boolean isLoad = course.getLoadTagAndCatalog();
        if(isLoad == null || !isLoad.booleanValue()){
            List<Course> simple_list = new ArrayList<>();
            for (Course c : list) {
                Course sc = new Course();
                sc.setId(c.getId());
                sc.setName(c.getName());
                simple_list.add(sc);
            }
            return simple_list;
        }
        return list;
    }
}
