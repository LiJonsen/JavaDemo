package JdkDecimalProxy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName DecimalProxy
 * @Description 动态代理类
 * @Author Josen
 * @Create 2020/7/25 19:55
 */
public final class DecimalProxy {
    // 方式一
    public static <E> E getProxyByInterface(Object obj){
        return (E) Proxy.newProxyInstance(
                DecimalProxy.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy,method,args)-> {
                    // invoke方法
                    String name = method.getName();
                    System.out.println("invoke before：method name="+name);
                    Object result = method.invoke(obj,args);
                    System.out.println("invoke after： result="+result);
                    return result;
                }
        );
    }

    // 方式二
    public static Object getProxyByExtend(Object obj) throws Exception {
        Class clazz = Proxy.getProxyClass(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces()
        );

        Constructor cons = clazz.getDeclaredConstructor(InvocationHandler.class);
        Object proxy = cons.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // invoke方法
                String name = method.getName();
                System.out.println("invoke before：method name="+name);
                Object result = method.invoke(obj,args);
                System.out.println("invoke after： result="+result);
                return result;
            }
        });
        return proxy;
    }
}
