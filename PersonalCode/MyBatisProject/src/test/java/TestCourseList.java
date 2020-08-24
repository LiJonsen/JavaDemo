import cn.touchfish.mm.dao.CourseMapper;
import cn.touchfish.mm.dao.QuestionMapper;
import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.framework.utils.ClassScannerUtils;
import cn.touchfish.mm.pojo.Company;
import cn.touchfish.mm.pojo.Course;
import cn.touchfish.mm.utils.JedisUtils;
import cn.touchfish.mm.utils.MybatisUtils;
import cn.touchfish.mm.utils.WxUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName TestCourseList
 * @Description Details
 * @Author Josen
 * @Create 2020/8/18 14:50
 */
public class TestCourseList {
    @Test
    public void getCourseList() throws IOException {
//        SqlSession sqlSession = MybatisUtils.openSqlSession();
//        QueryPageBean qpb = new QueryPageBean();
//        qpb.setCurrentPage(0);
//        qpb.setPageSize(10);
//        Map map = new HashMap();
//        map.put("name","p");
//        map.put("status",null);
//        qpb.setQueryParams(map);
//        List<Course> list = sqlSession.getMapper(CourseMapper.class).queryCourseListByPage(qpb);
//        System.out.println("list size="+list.size());
//        for (Course course : list) {
//            System.out.println(course);
//        }

//        MybatisUtils.commitAndClose(sqlSession);
    }

    @Test
    public void testCompanyList() throws IOException {
//        SqlSession sqlSession = MybatisUtils.openSqlSession();
//        List<Course> list = sqlSession.getMapper(CourseMapper.class).querySimpleList(0);
//        List<Course> simple_list = new ArrayList<>();
//        for (Course course : list) {
//            Course sc = new Course();
//            sc.setId(course.getId());
//            sc.setName(course.getName());
//            simple_list.add(sc);
//        }
//        System.out.println("list="+list);
//        MybatisUtils.commitAndClose(sqlSession);
    }

    @Test
    public void testGetResources() throws IOException {
     JSONObject wxPlatformMsg = WxUtil.get("093lhXZv3Gw4PU2obh1w3Akd1B4lhXZN");
        System.out.println(wxPlatformMsg);
    }
}
