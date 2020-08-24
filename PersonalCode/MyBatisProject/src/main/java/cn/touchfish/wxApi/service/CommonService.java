package cn.touchfish.wxApi.service;

import cn.touchfish.mm.dao.CourseMapper;
import cn.touchfish.mm.pojo.Course;
import cn.touchfish.mm.pojo.Dict;
import cn.touchfish.mm.utils.LocationUtil;
import cn.touchfish.mm.utils.MybatisUtils;
import cn.touchfish.mm.dao.DictMapper;
import cn.touchfish.wxApi.pojo.Location;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonService
 * @Description
 * @Author Josen
 * @Create 2020/8/23 12:01
 */
public class CommonService {
    /**
     * 获取学科列表
     * @return
     */
    public List<Course> getCourseList() throws IOException {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        List<Course> list = sqlSession.getMapper(CourseMapper.class).querySimpleList(0);
        List<Course> simple_list = new ArrayList<>();
        for (Course c : list) {
            Course sc = new Course();
            sc.setId(c.getId());
            sc.setName(c.getName());
            simple_list.add(sc);
        }
        MybatisUtils.commitAndClose(sqlSession);
        return simple_list;
    }

    /**
     * 获取城市列表
     * @return
     */
    public Map getCityList(Location location) throws IOException {
        // 1. 获取定位城市名字
        String city = LocationUtil.parseLocation(location.getLocation());
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        DictMapper mapper = sqlSession.getMapper(DictMapper.class);
        // 2. 根据名字查询数据库，获取Dict对象
        Dict dictByCityName = mapper.getDictByCityName(city);
        // 3. 更加fs类型，查询数据库城市列表
        List<Dict> dictList = mapper.getDictListByFs(location.getFs());

        // 4. 返回城市列表信息
        HashMap res = new HashMap();
        res.put("location",dictByCityName);
        res.put("citys",dictList);
        MybatisUtils.commitAndClose(sqlSession);
        return res;
    }
}
