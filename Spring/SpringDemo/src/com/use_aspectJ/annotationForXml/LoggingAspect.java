package com.use_aspectJ.annotationForXml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * @ClassName LoggingAspect
 * @Description AOP日志切面
 * @Author Josen
 * @Create 2020/7/27 12:39
 */
public class LoggingAspect {
    /**
     * 前置通知-在方法执行之前执行
     */
    public void beforeMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();

        System.out.printf("1. LoggingAspect For XML：执行%s(%s)方法@Before前置通知...\n",name, Arrays.toString(args));
    }

    /**
     * @After后置通知-在方法执行之后执行
     */
    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();

        System.out.printf("3. LoggingAspect For XML：执行%s方法的@After后置通知...\n",name);
    }

    /**
     * 返回通知，在方法返回结果之后执行
     */
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("@AfterReturning返回通知，在%s(%s)方法返回结果=%d\n",name,Arrays.toString(args),result);
    }

    /**
     * 异常通知: 在目标方法抛出异常后执行.
     */
    public void afterThrowingMethod(JoinPoint joinPoint,Exception err){
        System.out.println("执行@AfterThrowing异常通知:"+err);
    }
}
