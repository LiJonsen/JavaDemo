package com.review.Proxy;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @Description 代理工厂类
 * 根据不同的被代理类，动态生成一个代理类
 * @Author Josen
 * @Date 2020/6/23 19:53
 * @Version 1.0
 **/
public class ProxyFactory {
    /**
     * 根据被代理类动态生成一个代理类
     * @param obj 被代理类对象
     * @return
     */
    public static Object getProxyInstance(Object obj){
        // 1. 获取被代理类对象ClassLoader
        ClassLoader classLoader = obj.getClass().getClassLoader();
        // 2. 获取被代理类对象Interfaces
        Class<?>[] interfaces = obj.getClass().getInterfaces();

        // 3.1 实例化InvocationHandle接口实现类
        MyInvocationHandle handle = new MyInvocationHandle();
        // 3.2 传入被代理类对象
        handle.bind(obj);

        // 4. 动态创建代理类并返回
        Object proxy_obj = Proxy.newProxyInstance(classLoader, interfaces, handle);
        return proxy_obj;
    }
}
