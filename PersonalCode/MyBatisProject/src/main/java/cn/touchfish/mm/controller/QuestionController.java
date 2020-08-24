package cn.touchfish.mm.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.PageResult;
import cn.touchfish.mm.entity.QueryPageBean;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.PreAuthorize;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.pojo.Company;
import cn.touchfish.mm.pojo.Dict;
import cn.touchfish.mm.pojo.Question;
import cn.touchfish.mm.pojo.ReviewLog;
import cn.touchfish.mm.service.QuestionService;
import cn.touchfish.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QuestionController
 * @Description 处理基础题库请求
 * @Author Josen
 * @Create 2020/8/19 15:39
 */
@Controller
public class QuestionController {
    private QuestionService questionService = new QuestionService();

    /**
     * 分页查询基础题库列表接口
     */
    @RequestMapping("/question/getList")
    @PreAuthorize("QUESTION_LIST")
    public void handlerQuestionListByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(req, QueryPageBean.class);
            PageResult listByPage = questionService.getQuestionListByPage(queryPageBean);
            result.setResult(listByPage);
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            JsonUtils.printResult(resp,result);

        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    @RequestMapping("/question/dictList")
    public void handlerDictList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);

        try {
            List<Dict> dictList = questionService.getDictList();
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            result.setResult(dictList);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }
    /**
     * 获取企业列表以及企业对应的方向
     */
    @RequestMapping("/question/companyList")
    public void handlerCompanyList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            List<Company> companyList = questionService.getCompanyList();
            result.setCode(200);
            result.setMessage(Constants.SERVICE_SUCCESS);
            result.setResult(companyList);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 新增基础试题
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/question/save")
    public void handlerAddQuestion(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Question question = JsonUtils.parseJSON2Object(req, Question.class);
            boolean status = questionService.addOneQuestion(question);
            if(status){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("添加基础试题失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }

    /**
     * 添加基础试题到精选
     */
    @RequestMapping("/question/addToClassic")
    public void handlerAddToClassic(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            ReviewLog reviewLog = JsonUtils.parseJSON2Object(req, ReviewLog.class);
            boolean status = questionService.addToClassic(reviewLog);
            if(status){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("添加基础试题到精选试题失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }
    /**
     * 处理精选试题审核
     */
    @RequestMapping("/question/reviewQuestion")
    public void handlerReviewQuestion(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            Question question = JsonUtils.parseJSON2Object(req, Question.class);
            boolean status = questionService.reviewQuestion(question);
            if(status){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("处理精选试题审核失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(resp,result);
        }
    }
}
