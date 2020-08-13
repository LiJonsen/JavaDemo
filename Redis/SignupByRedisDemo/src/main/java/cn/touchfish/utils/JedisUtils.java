package cn.touchfish.utils;

import redis.clients.jedis.Jedis;

/**
 * @ClassName JedisUtils
 * @Description Redis 工具类
 * @Author Josen
 * @Create 2020/8/11 11:12
 */
public final class JedisUtils {
    public static Jedis getJedis(){
        Jedis jedis = new Jedis("106.53.88.252", 6380);
        if("PONG".equals(jedis.ping())){
            return jedis;
        }
        throw new RuntimeException("无法连接Redis服务器...");
    }
}
