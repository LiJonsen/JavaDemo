package Class_day7_13.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @Description 代理工厂（根据被代理类动态生成代理类）
 * @Author Josen
 * @Create 17:26 17:26
 */
public final class ProxyFactory {
    /**
     * 方式一：提供被代理类调用，动态生成一个代理类并返回
     * @return 代理类
     */
    public static <E> E getProxy(Object obj){
        /**
         * arg1=ClassLoader loader, 被代理类ClassLoader
         * arg2=Class<?>[] interfaces, 被代理类Interfaces
         * arg3=InvocationHandler h  当前代理类this
         */
        return (E) Proxy.newProxyInstance(
                ProxyFactory.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始生产前的准备工作.....");
                        Object res = method.invoke(obj, args);
                        System.out.println("开始生产完成后的收尾工作.....");
                        return res;
                    }
                });
    }
    /**
     * 方式二：提供被代理类调用，动态生成一个代理类并返回
     * @return 代理类
     */
    public static <T> T createProxy(Object obj){
        HandlerProxy handle = new HandlerProxy();
        handle.bind(obj);
        return (T) Proxy.newProxyInstance(
                ProxyFactory.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                handle
        );
    }
}


class HandlerProxy implements InvocationHandler{
    private Object obj;
    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "HandlerProxy{" +
                "obj=" + obj +
                '}';
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始生产前的准备工作.....");
        Object res = method.invoke(obj, args);
        System.out.println("开始生产完成后的收尾工作.....");
        return res;
    }
}