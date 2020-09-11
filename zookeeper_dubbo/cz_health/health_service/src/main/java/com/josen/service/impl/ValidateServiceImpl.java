package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.josen.service.ValidateService;
import com.josen.utils.SMSUtils;
import com.josen.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName ValidateServiceImpl
 * @Description 实现验证码业务逻辑
 * @Author Josen
 * @Create 2020/9/11 16:04
 */
@Service(interfaceClass = ValidateService.class)
@Transactional
public class ValidateServiceImpl implements ValidateService {
    @Autowired
    private JedisPool jedisPool;
    @Override
    public void sendCode(String phoneNumber,String type) throws ClientException {
        // 1. 获取6位随机验证码
        String code = ValidateCodeUtils.generateValidateCode(6).toString();
        // 2. 发送短信验证码
//        SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,phoneNumber, code);
        System.out.println("phoneNumber=====>"+phoneNumber);
        System.out.println("code=====>"+code);
        // 3. 存储验证码到redis,五分钟过期时间
        Jedis resource = jedisPool.getResource();
        resource.setex(type+phoneNumber,60*5,code);
        resource.close();
    }
}
