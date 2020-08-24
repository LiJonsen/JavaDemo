package cn.touchfish.wxApi.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.pojo.Course;
import cn.touchfish.mm.utils.JsonUtils;
import cn.touchfish.wxApi.pojo.Location;
import cn.touchfish.wxApi.service.CommonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonController
 * @Description 小程序Common接口
 * @Author Josen
 * @Create 2020/8/23 10:29
 */
@Controller
public class CommonController {
    private CommonService commonService = new CommonService();
    /**
     * 获取城市列表请求(fs对应数据库t_dict data_tag字段)
     * fs=0：不首页显示 - 所有城市
     * fs=1：首页显示-热门城市
     * @param req
     * @param resp
     */
    @RequestMapping("/wxapi/common/cities")
    public void handlerCitiesRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Location location = JsonUtils.parseJSON2Object(req, Location.class);

            Map cityMap = commonService.getCityList(location);
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            result.setResult(cityMap);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            JsonUtils.printResult(resp,result);
            e.printStackTrace();
        }

    }

    /**
     * 获取学科列表
     * @param req
     * @param resp
     */
    @RequestMapping("/wxapi/common/courses")
    public void handlerCoursesRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            List<Course> courseList = commonService.getCourseList();
            result.setResult(courseList);
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }
}
