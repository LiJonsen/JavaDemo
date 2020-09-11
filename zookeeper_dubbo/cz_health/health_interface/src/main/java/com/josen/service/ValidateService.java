package com.josen.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @InterfaceName ValidateService
 * @Description 验证码业务逻辑
 * @Author Josen
 * @Create 2020/9/11 15:48
 */
public interface ValidateService {
    /**
     * 发送验证码到指定手机号
     * @param phoneNumber
     * @param type 短信验证码类型（参考RedisConstant实体类）
     */
    void sendCode(String phoneNumber,String type) throws ClientException;
}
