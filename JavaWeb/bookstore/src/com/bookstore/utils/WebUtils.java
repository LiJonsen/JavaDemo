package com.bookstore.utils;

import com.bookstore.pojo.ResponseData;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ClassName WebUtils
 * @Description Web层通用工具类
 * @Author Josen
 * @Date 2020/6/9 15:52
 * @Version 1.0
 **/
public class WebUtils {
    /**
     * 将请求参数转换成bean对象并返回
     * @param value
     * @param bean
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> T TransferParamsToBean(Map value,T bean) throws NoSuchFieldException {
        try {
            System.out.println("注入之前：" + bean);
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串数值转换成int类型并返回
     * @param str
     * @param defaultNum 默认值
     * @return
     */
    public static int ParseStringToInt(String str,int defaultNum){
        try{
            return Integer.parseInt(str);
        }catch (RuntimeException e){
            return defaultNum;
        }
    }
    /**
     * 校验验证码
     * @param req
     * @return true=验证码正确 false=验证码错误
     */
    public static boolean CheckedCode(HttpServletRequest req){
        // 校验验证码
        String real_code = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 获取之后，立即清除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        System.out.println("code:"+code);
        System.out.println("real_code:"+real_code);
        return real_code!=null && real_code.equals(code);
    }

    /**
     * 设置通用Session响应数据信息
     * @param req
     * @param result
     * @param <E>
     */
    public static <E> void SetCommonResultMsg(HttpServletRequest req,ResponseData<E> result){
        req.getSession().setAttribute("commonRes",result);
    }

    /**
     * 清除通用Session响应数据
     */
    public static void RemoveCommonResMsg(HttpServletRequest req){
        Object obj = req.getSession().getAttribute("result");
        if(obj!=null){
            req.getSession().removeAttribute("result");
        }
    }
}
