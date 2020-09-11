package com.josen.entity;

/**
 * @ClassName RedisConstant
 * @Description
 * @Author Josen
 * @Create 2020/9/8 16:16
 */
public class RedisConstant {
    //套餐图片所有图片名称-未保存到数据库
    public static final String SETMEAL_PIC_RESOURCES = "setmealPicResources";
    //套餐图片所有图片名称-已保存在数据库
    public static final String SETMEAL_PIC_DB_RESOURCES = "setmealPicDbResources";

    // 存储发送手机短信验证码类型
    // 1. 提交体检预约验证码
    public static final String SEND_CODE_TYPE_ORDER = "order";
    // 2. 登录验证码
    public static final String SEND_CODE_TYPE_LOGIN = "login";
    // 3. 找回密码验证码
    public static final String SEND_CODE_TYPE_FIND_PWD = "forgetPwd";

}
