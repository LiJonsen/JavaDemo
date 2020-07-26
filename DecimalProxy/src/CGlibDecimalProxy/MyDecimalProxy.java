package CGlibDecimalProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @ClassName MyDecimalProxy
 * @Description CGlib动态代理
 * @Author Josen
 * @Create 2020/7/25 23:00
 */
public class MyDecimalProxy implements MethodInterceptor {
    /**
     * 接收被代理类，动态生成代理类并返回
     * @param cls 被代理类Class
     * @param argumentTypes  参数类型
     * @param arguments 参数
     * @return Class的CGLIB代理对象
     */
//    public Object getProxy(Class cls,Class[] argumentTypes, Object[] arguments){
//        //CGLIB enhancer增强类对象
//        Enhancer enhancer = new Enhancer();
//        //设置增强类型
//        enhancer.setSuperclass(cls);
//        //定义代理对象为当前对象，要求当前对象实现MethodInterceptor方法
//        enhancer.setCallback(this);
//        //生成并返回代理对象
//        return enhancer.create(argumentTypes ,arguments);
//    }

    /**
     * @param proxy 代理对象
     * @param method 方法
     * @param args 方法参数
     * @param methodProxy 方法代理
     * @return 代理逻辑返回
     * @throws Throwable 抛出异常
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用真实对象前");

        //CGLIB反射调用真实对象方法
        Object result = methodProxy.invokeSuper(proxy, args);

        System.out.println("调用真实对象后");

        return result;
    }
}
