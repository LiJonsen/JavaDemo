package com.use_aspectJ.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName LoggingAspect
 * @Description AOP日志切面
 * @Author Josen
 * @Create 2020/7/27 12:39
 */
@Component
@Aspect
@Order(2)
public class LoggingAspect {
    /**
     * 声明通用切入点表达式
     * execution(* com.use_aspectJ.annotation.*.*(..))
     *    *  : 任意修饰符 任意返回值
     *    *  : 任意类
     *    *  : 任意方法
     *    .. : 任意参数列表
     */
    @Pointcut("execution(* com.use_aspectJ.annotation.*.*(..))")
    public void declarePointcut(){};


    /**
     * 前置通知-在方法执行之前执行
     * @Before(切入点表达式)
     */
    @Before("declarePointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();

        System.out.printf("1. LoggingAspect：执行%s(%s)方法@Before前置通知...\n",name, Arrays.toString(args));
    }

    /**
     * @After后置通知-在方法执行之后执行
     * 连接点对象: JoinPoint
     */
    @After("declarePointcut()")
    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();

        System.out.printf("3. LoggingAspect：执行%s方法的@After后置通知...\n",name);
    }

    /**
     * @AfterReturning返回通知，在方法返回结果之后执行
     * 语法格式：
     * execution([权限修饰符] [返回值类型] [简单类名/全类名] [方法名]([参数列表]))
     */
    @AfterReturning(
            value = "execution(public int com.use_aspectJ.annotation.CalculatorImpl.add(int,int))",
            returning = "result"
    )
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("@AfterReturning返回通知，在%s(%s)方法返回结果=%d\n",name,Arrays.toString(args),result);
    }

    /**
     * @AfterThrowing异常通知: 在目标方法抛出异常后执行.
     * 通过throwing来指定一个名字， 必须要与方法的一个形参名一致.
     * 可以通过形参中异常的类型 来设置抛出指定异常才会执行异常通知.
     */
    @AfterThrowing(value = "declarePointcut()",throwing = "err")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception err){
        System.out.println("执行@AfterThrowing异常通知:"+err);
    }
}
