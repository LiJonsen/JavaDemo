package Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理示例
 */

// 1. 创建一或多个被代理类；

interface Human<T>{
    void eat();
    String goWork(T type);
}
class Women implements Human<String>{
    private String name;
    public Women(){}
    public Women(String name){
        this.name = name;
    }
    @Override
    public void eat() {
        System.out.println(name+"斯斯文文的吃饭");
    }
    @Override
    public String goWork(String type) {
        return name+"从事"+type+"工作";
    }
}



//2.1 提供一个公用静态方法，用于动态创建代理类实例对象；
class ProxyClass{
    /**
     * 动态创建代理类
     * @param obj 当前被代理类
     * @return  动态代理类实例对象
     */
    public static Object CreateProxy(Object obj){
        ProxyHandle handle = new ProxyHandle();
        handle.bind(obj);
        Class clazz = obj.getClass();
        // Proxy.newProxyInstance(被代理类class, 被代理类接口, InvocationHandler实现类的对象)
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),handle);
    }
}
// 2.2 InvocationHandler实现类【是代理实例的调用处理程序 实现的接口】
class ProxyHandle implements InvocationHandler{
    // 当前被代理类
    private Object obj;
    public void bind(Object obj){
        this.obj = obj;
    }
    /**
     * 动态代理类方法调用
     * @param proxy 在其上调用方法的代理实例
     * @param method 当前调用的被代理方法
     * @param args 方法参数列表
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        return result;
    }
}

/**
 * 3. 实例化被代理类，传入创建代理类的静态方法中，
 *    返回某个被代理类特定的`代理类实例对象`，
 *    通过该对象调用被代理类中的同名方法
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        System.out.println("===============被代理类Women===============");

        // 1 创建Women被代理类
        Human jeni = new Women("Jeni");
        // 2. 传入被代理类，动态创建代理类实例对象
        Human jeni_proxy = (Human) ProxyClass.CreateProxy(jeni);
        // 3. 通过代理类调用指定方法
        jeni_proxy.eat();
        String type = jeni_proxy.goWork("主播");
        System.out.println(type);

        System.out.println("===============被代理类Nike===============");

        clothFactory factory = new NikeClothFactory();
        clothFactory factory_proxy = (clothFactory) ProxyClass.CreateProxy(factory);
        factory_proxy.produceFactory();
    }
}
