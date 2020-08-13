package com.company.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisPool
 * @Description Singleton For Jedis Pool
 * 懒汉式
 * @Author Josen
 * @Create 2020/8/6 12:05
 */
public final class MyJedisPool {
    // 设置redis pool可用连接实例最大数量【默认：8】
    private static int MAX_TOTAL = 1024;
    // 设置一个pool最多可以有多少个空闲(Idle)的实例【默认：8】
    private static int MAX_IDLE = 100;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    // 如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT_MILLIS = 10000;

    private static String HOST = "42.194.197.225";
//    private static String HOST = "106.53.88.252";
    // cluster slot 1
    public static final int MASTER_PORT01 = 6379;
    // cluster slot 2
    public static final int MASTER_PORT02 = 6380;
    // cluster slot 3
    public static final int MASTER_PORT03 = 6381;


    private static String AUTH = "root";
    // 设置连接redis远程服务器超时
    private static int LINK_HOST_TIMEOUT = 10000;

    // 单例Jedis连接池
    private static JedisPool jedisPool_6379;
    private static JedisPool jedisPool_6380;
    private static JedisPool jedisPool_6381;
    private static JedisPool getJedisPool(JedisPool jedisPool,int port){
        if(jedisPool == null){
            synchronized(MyJedisPool.class){
                if(jedisPool==null){
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(MAX_TOTAL);
                    config.setMaxIdle(MAX_IDLE);
                    config.setMaxWaitMillis(MAX_WAIT_MILLIS);
                    jedisPool = new JedisPool(config,HOST,port,LINK_HOST_TIMEOUT);
                }
            }
        }
        return jedisPool;
    }
    public static JedisPool getJedisPool(int port){
        switch (port){
            case MASTER_PORT01:
                return getJedisPool(jedisPool_6379,port);
            case MASTER_PORT02:
                return getJedisPool(jedisPool_6380,port);
            case MASTER_PORT03:
                return getJedisPool(jedisPool_6381,port);
        }
        return null;
    }

}
