package cn.touchfish.mm.utils;
import cn.touchfish.mm.utils.Jedis.MyJedisCmdImpl;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName DecimalProxy
 * @Description 动态代理类
 * @Author Josen
 * @Create 2020/8/14 16:50
 */
public final class DecimalProxy {
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(
                DecimalProxy.class.getClassLoader(),
                obj.getClass().getInterfaces(),
                handler
        );
    }

}

class MyInvocationHandler implements InvocationHandler{
    private Object obj;
    public void bind(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res=null;
        String name = obj.getClass().getName();
        String jedis_name = MyJedisCmdImpl.class.getName();
        if(jedis_name.equals(name)){
            // 处理Redis连接
            Jedis jedis = JedisUtils.getJedis();
            MyJedisCmdImpl myJedisCmd = (MyJedisCmdImpl) obj;
            myJedisCmd.setJedis(jedis);
            res = method.invoke(obj, args);
            jedis.close();
        }else{
             res = method.invoke(obj, args);
        }
        return res;
    }
}
