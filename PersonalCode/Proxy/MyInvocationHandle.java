package com.review.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandle
 * @Description InvocationHandler接口实现类
 * 当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
 * @Author Josen
 * @Date 2020/6/23 19:54
 * @Version 1.0
 **/
public class MyInvocationHandle implements InvocationHandler {
    private Object obj;// 被代理类
    public void bind(Object obj){
        this.obj = obj;
    }
    /**
     * 通过InvocationHandler接口的实现类及其方法invoke()
     * 当通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
     * @param proxy
     * @param method 当前调用方法
     * @param args 方法形参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(obj, args);
        System.out.println("**********执行MyInvocationHandle类中的invoke方法**************");
        return invoke;
    }
}
