package cn.touchfish.wxApi.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.utils.JsonUtils;
import cn.touchfish.wxApi.pojo.CategoryItem;
import cn.touchfish.wxApi.pojo.CategoryParam;
import cn.touchfish.wxApi.service.CategoryService;
import cn.touchfish.wxApi.utils.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description
 * @Author Josen
 * @Create 2020/8/23 16:21
 */
@Controller
public class CategoryController {
    private CategoryService categoryService = new CategoryService();
    /**
     * 处理小程序获取面试题库列表请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/wxapi/category/list")
    public void handlerCategoryList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String authorization = ServletUtil.getReqHeaderToken(req);
            Result result = new Result(200, Constants.SERVICE_SUCCESS);
            CategoryParam categoryParam = JsonUtils.parseJSON2Object(req, CategoryParam.class);
            categoryParam.setOpenId(authorization);
            List<CategoryItem> categoryItems = categoryService.getCategoryItems(categoryParam);
            result.setResult(categoryItems);
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            JsonUtils.printResult(resp,new Result(500));
            e.printStackTrace();
        }
    }
}
