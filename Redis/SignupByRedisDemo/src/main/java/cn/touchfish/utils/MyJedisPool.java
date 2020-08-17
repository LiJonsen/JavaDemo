package cn.touchfish.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName JedisPool
 * @Description Redis连接池单例模式
 * @Author Josen
 * @Create 2020/8/14 9:26
 */
public final class MyJedisPool {
    // 设置redis pool可用连接实例最大数量【默认：8】
    private static int MAX_TOTAL;
    // 设置一个pool最多可以有多少个空闲(Idle)的实例【默认：8】
    private static int MAX_IDLE;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    // 如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT_MILLIS;
    // 设置连接redis远程服务器超时
    private static int LINK_HOST_TIMEOUT;

    private static String HOST;
    private static int PORT;

    public static String AUTH;

    // 初始化Jedis连接池配置参数
    static {
        InputStream stream = null;
        try {
            stream = MyJedisPool.class.getClassLoader().getResource("redis.properties").openStream();
            Properties properties = new Properties();
            properties.load(stream);

            String maxTotal = properties.getProperty("maxTotal");
            String maxIdle = properties.getProperty("maxIdle");
            String maxWaitMillis = properties.getProperty("maxWaitMillis");
            String LinkHostTimeout = properties.getProperty("LinkHostTimeout");
            String redisHost = properties.getProperty("redisHost");
            String redisPort = properties.getProperty("redisPort");
            String redisAuth = properties.getProperty("redisAuth");
            MAX_TOTAL = Integer.parseInt(maxTotal);
            MAX_IDLE = Integer.parseInt(maxIdle);
            MAX_WAIT_MILLIS = Integer.parseInt(maxWaitMillis);
            LINK_HOST_TIMEOUT = Integer.parseInt(LinkHostTimeout);
            HOST = redisHost;
            PORT = Integer.parseInt(redisPort);
            AUTH = redisAuth;
            properties.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            CommonUtils.closeStream(stream);
        }

    }

    private static JedisPool jedisPool;

    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (MyJedisPool.class) {
                if (jedisPool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(MAX_TOTAL);
                    config.setMaxIdle(MAX_IDLE);
                    config.setMaxWaitMillis(MAX_WAIT_MILLIS);
                    jedisPool = new JedisPool(config, HOST, PORT, LINK_HOST_TIMEOUT);
                }
            }
        }
        return jedisPool;
    }
}
