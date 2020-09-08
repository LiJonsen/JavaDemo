package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.MessageConstant;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.entity.Result;
import com.josen.pojo.CheckGroup;
import com.josen.pojo.CheckItem;
import com.josen.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CheckGroupController
 * @Description 处理检查组请求
 * @Author Josen
 * @Create 2020/9/6 20:34
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
    /**
     * 获取所有检查组列表
     */
    @RequestMapping(value = "/queryAllGroup",method = RequestMethod.GET)
    @ResponseBody
    public Result handlerQueryAllItems(){
        List<CheckGroup> items = checkGroupService.queryAllGroup();
        Result result = new Result(true, MessageConstant.REQ_SUCCESS,items);
        return result;
    }

    /**
     * 处理分页查询检查组请求
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerQueryList(@RequestBody QueryPageBean pageBean){
        if(pageBean == null){
            // 设置默认参数
            pageBean = new QueryPageBean();
            pageBean.setPageSize(10);
            pageBean.setCurrentPage(1);
        }
        PageResult list = checkGroupService.queryListByPage(pageBean);
        Result result = new Result(true, MessageConstant.REQ_SUCCESS,list);
        return result;
    }

    /**
     * 处理添加检查组请求
     * @param checkGroup
     * @return
     */
    @RequestMapping(value = "/addGroup",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerAddGroup(@RequestBody CheckGroup checkGroup){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(checkGroup != null){
            checkGroupService.addCheckGroup(checkGroup);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }

    /**
     * 处理编辑检查组请求
     * @param checkGroup
     * @return
     */
    @RequestMapping(value = "/editGroup",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerEditGroup(@RequestBody CheckGroup checkGroup){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(checkGroup != null){
            checkGroupService.editCheckGroup(checkGroup);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }

    /**
     * 处理删除检查组请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGroup",method = RequestMethod.GET)
    @ResponseBody
    public Result handlerDeleteGroup(Integer id){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(id != null){
            checkGroupService.deleteCheckGroup(id);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }
}
