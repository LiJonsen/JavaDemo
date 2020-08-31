package com.josen.aspect;

import com.josen.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TransactionAdvice
 * @Description 处理事务切面
 * @Author Josen
 * @Create 2020/8/27 12:30
 */
@Component
@Aspect
public class TransactionAdvice {
    @Autowired
    private TransactionManager txManager;
    /**
     * 环绕通知-统一处理DAO层的事务
     * @return
     */
    @Around("execution(* com.josen.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint){
        Object res = null;
        try {
            txManager.CreateCurrentThreadConn();
            res = joinPoint.proceed();
            txManager.commitAndClose();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            txManager.rollbackAndClose();
        }

        return res;
    }

}
