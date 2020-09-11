package com.josen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.josen.entity.RedisConstant;
import com.josen.entity.Result;
import com.josen.service.ValidateService;
import com.josen.utils.WebUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName ValidateController
 * @Description 处理短信通知请求
 * @Author Josen
 * @Create 2020/9/11 16:10
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {
    @Reference
    private ValidateService validateService;

    /**
     * 发送短信验证码
     * type=order ： 体检预约
     * type=login ： 登录
     * type=forgetPwd ： 找回密码
     * @param body
     * @return
     */
    @RequestMapping("/sendCode")
    public Result handlerSendCode(@RequestBody Map body){
        try {
            String phone = (String)body.get("phone");
            String type = (String)body.get("type");

            if(!RedisConstant.SEND_CODE_TYPE_ORDER.equals(type) &&
               !RedisConstant.SEND_CODE_TYPE_LOGIN.equals(type) &&
               !RedisConstant.SEND_CODE_TYPE_FIND_PWD.equals(type)
            ){
                // type类型无效
                return WebUtils.getFailResult();
            }
            validateService.sendCode(phone, RedisConstant.SEND_CODE_TYPE_ORDER);
            return WebUtils.getSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return WebUtils.getFailResult();
        }
    }
}
