package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.MessageConstant;
import com.josen.entity.PageResult;
import com.josen.entity.QueryPageBean;
import com.josen.entity.Result;
import com.josen.pojo.CheckGroup;
import com.josen.pojo.Setmeal;
import com.josen.service.SetmealService;
import com.josen.utils.QiNiuUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName SetmealController
 * @Description 处理套餐页面请求
 * @Author Josen
 * @Create 2020/9/7 18:04
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    /**
     * 处理套餐列表分页查询请求
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "queryList",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerQueryList(@RequestBody QueryPageBean pageBean){
        if(pageBean == null){
            // 设置默认参数
            pageBean = new QueryPageBean();
            pageBean.setPageSize(10);
            pageBean.setCurrentPage(1);
        }
        PageResult list = setmealService.queryListByPage(pageBean);
        Result result = new Result(true, MessageConstant.REQ_SUCCESS,list);
        return result;
    }

    /**
     * 处理添加套餐请求
     * @param setmeal
     * @return
     */
    @RequestMapping(value = "addSetmeal",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerQueryList(@RequestBody Setmeal setmeal){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(setmeal != null){
            setmealService.addSetmeal(setmeal);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }

    /**
     * 处理上传图片到七牛云请求
     * @param imgFile
     * @return
     */
    @RequestMapping(value = "uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerUploadImage(@RequestParam("imgFile") MultipartFile imgFile){

        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        try {
            if(imgFile != null){
                String message = QiNiuUtil.uploadImage(imgFile.getBytes(), imgFile.getOriginalFilename());
                result.setMessage(message);

                if(!MessageConstant.PIC_UPLOAD_FORMAT_ERROR.equals(message) && !MessageConstant.PIC_UPLOAD_FAIL.equals(message)){
                    // 上传成功
                    result.setMessage(MessageConstant.PIC_UPLOAD_SUCCESS);
                    result.setFlag(true);
                    result.setData(message);
                }
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
    }


    /**
     * 处理编辑检查组请求
     * @param setmeal
     * @return
     */
    @RequestMapping(value = "/editSetmeal",method = RequestMethod.POST)
    @ResponseBody
    public Result handlerEditGroup(@RequestBody Setmeal setmeal){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(setmeal != null){
            setmealService.editSetmeal(setmeal);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }

    /**
     * 处理删除检查组请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSetmeal",method = RequestMethod.GET)
    @ResponseBody
    public Result handlerDeleteGroup(Integer id){
        Result result = new Result(false,MessageConstant.REQ_PARAM_ERROR,"");
        if(id != null){
            setmealService.deleteSetmeal(id);
            result = new Result(true,MessageConstant.REQ_SUCCESS,"");
        }
        return result;
    }
}
