package cn.touchfish.mm.utils;

import cn.touchfish.mm.utils.Jedis.MyJedisCmdImpl;
import cn.touchfish.mm.utils.Jedis.MyJedisCommands;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName JedisUtils
 * @Description Jedis工具类
 * @Author Josen
 * @Create 2020/8/14 19:20
 */
public final class JedisUtils {

    private static final JedisPool jedisPool = MyJedisPool.getJedisPool();

    /**
     * 返回动态代理生成的Jedis命令工具实例
     * 【使用该方法操作Redis】
     * @return
     */
    public static MyJedisCommands getJedisCmd(){
        MyJedisCommands myJedisCmd = new MyJedisCmdImpl();
        return (MyJedisCommands)DecimalProxy.getProxyInstance(myJedisCmd);
    }

    /**
     * 获取Jedis连接实例
     * @return
     */
    public static Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(MyJedisPool.AUTH);
        if("PONG".equals(jedis.ping())){
            return jedis;
        }
        jedis.close();
        throw new RuntimeException("无法连接Redis服务器...");
    }

}
