package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.josen.entity.Result;
import com.josen.pojo.Setmeal;
import com.josen.service.SetmealService;
import com.josen.utils.WebUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MobileSetmealController
 * @Description 处理移动端套餐请求
 * @Author Josen
 * @Create 2020/9/10 16:53
 */
@RestController
@RequestMapping("/setmeal")
public class MobileSetmealController {
    @Reference
    private SetmealService setmealService;
    /**
     * 查询套餐列表请求
     * @return
     */
    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    public Result handlerQueryList(){
        try {
            List<Setmeal> list = setmealService.queryAllSetmeal();
            return WebUtils.getSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtils.getFailResult();
        }
    }

    /**
     * 获取套餐详情信息
     * @return
     */
    @RequestMapping(value = "/getDetails",method = RequestMethod.POST)
    public Result handlerGetDetails(@RequestBody Setmeal setmeal){
        try {
            Setmeal details = setmealService.queryDetails(setmeal);
            return WebUtils.getSuccessResult(details);
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtils.getFailResult();
        }
    }
}
