package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.MessageConstant;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.entity.Result;
import com.josen.pojo.CheckItem;
import com.josen.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CheckItemController
 * @Description 处理检查项请求
 * @Author Josen
 * @Create 2020/9/6 15:44
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    /**
     * 分页查询检查项列表
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerGetCheckItems(@RequestBody QueryPageBean pageBean){
        if(pageBean == null){
            // 设置默认参数
            pageBean = new QueryPageBean();
            pageBean.setPageSize(10);
            pageBean.setCurrentPage(1);
        }
        PageResult list = checkItemService.queryListByPage(pageBean);
        Result result = new Result(true, MessageConstant.REQ_SUCCESS,list);
        return result;
    }

    @RequestMapping(value = "/queryAllItems",method = RequestMethod.GET)
    @ResponseBody
    public Result handlerQueryAllItems(){
        List<CheckItem> items = checkItemService.queryAllItem();
        Result result = new Result(true, MessageConstant.REQ_SUCCESS,items);
        return result;
    }

    /**
     * 添加检查项
     * @param checkItem
     * @return
     */
    @RequestMapping(value = "/addItem",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerAddItem(@RequestBody CheckItem checkItem){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(checkItem != null){
            checkItemService.addCheckItem(checkItem);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }

    /**
     * 编辑检查项
     * @param checkItem
     * @return
     */
    @RequestMapping(value = "/editItem",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerEditItem(@RequestBody CheckItem checkItem){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(checkItem != null){
            checkItemService.editCheckItem(checkItem);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }
    /**
     * 删除检查项
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteItem",method = RequestMethod.GET)
    @ResponseBody
    public Result handlerDeleteItem(@RequestParam("id") Integer id){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(id != null){
            checkItemService.deleteCheckItem(id);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }
}
