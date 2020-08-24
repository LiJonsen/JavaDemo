package cn.touchfish.mm.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.PageResult;
import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.PreAuthorize;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.pojo.Course;
import cn.touchfish.mm.service.CourseService;
import cn.touchfish.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseController
 * @Description 学科管理控制器
 * @Author Josen
 * @Create 2020/8/18 15:06
 */
@Controller
public class CourseController {
    private CourseService courseService = new CourseService();
    /**
     * 处理学科管理列表分页查询接口
     */
    @RequestMapping("/course/list")
    @PreAuthorize("COURSE_LIST")
    public void handlerCourseList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(req, QueryPageBean.class);
            System.out.println("queryPageBean="+queryPageBean);
            PageResult listByPage = courseService.getCourseListByPage(queryPageBean);
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            result.setResult(listByPage);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 添加学科接口
     */
    @RequestMapping("/course/add")
    public void handlerAddCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
            boolean status = courseService.addCourse(course);
            if(status){
                // 新增成功
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("新增学科失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 修改一条学科信息
     */
    @RequestMapping("/course/modify")
    public void handlerModifyCourse(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);

        try {
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
            boolean status = courseService.modifyCourse(course);
            if(status){
                // 修改成功
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("修改失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 根据id删除一条学科记录
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/course/delete")
    public void handlerDeleteCourse(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Map params = JsonUtils.parseJSON2Object(req, HashMap.class);
            int courseId = (Integer)params.get("courseId");
            boolean status = courseService.deleteCourse(courseId);
            if(status){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("删除学科失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 获取学科id&name列表
     */
    @RequestMapping("/course/getCourseList")
    public void handlerCourseSimpleList(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Course course = JsonUtils.parseJSON2Object(req, Course.class);
            List<Course> simpleList = courseService.getSimpleList(course);
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            result.setResult(simpleList);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }
}
