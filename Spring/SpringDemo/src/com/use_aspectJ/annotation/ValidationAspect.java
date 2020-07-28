package com.use_aspectJ.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName ValidationAspect
 * @Description AOP校验切面
 * @Author Josen
 * @Create 2020/7/27 17:19
 */
@Component
@Aspect
@Order(1)
public class ValidationAspect {
    /**
     * 环绕通知
     * 可以理解是 前置 后置 返回  异常 通知的结合体，更像是动态代理的整个过程
     */
    @Around("LoggingAspect.declarePointcut()")
    public Object aroundMethod(ProceedingJoinPoint pjp){

        String name = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        try {
            // 前置
            System.out.printf("ValidationAspect:执行%s(%s)前置通知...\n",name, Arrays.toString(args));
            Object result = pjp.proceed();
            // 返回
            return result;
        } catch (Throwable e) {
            // 异常
            e.printStackTrace();
        }finally {
            // 后置
            System.out.printf("ValidationAspect:执行%s(%s)后置通知...\n",name, Arrays.toString(args));
        }
        return null;
    }
}
