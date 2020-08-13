package com.company.junit;

import com.company.utils.MyJedisPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName TestJedis
 * @Description Details
 * @Author Josen
 * @Create 2020/8/6 9:14
 */
public class TestJedis {
    public static void main(String[] args) throws InterruptedException {

//        Jedis jedis = new Jedis("106.53.88.252", 6380);
//        jedis.auth("root");
        // 使用jedis 连接池
        Runnable r1 = ()->{
            TestJedis.setRedisKV("jedis6379","Test 6379 Jedis",MyJedisPool.MASTER_PORT01);
//            JedisCluster cluster = new JedisCluster(new HostAndPort("106.53.88.252", 6379));
//            System.out.println(cluster);
//            cluster.set("test","testing");
        };
        Runnable r2 = ()->{
            TestJedis.setRedisKV("jedis6380","Test 6380 Jedis",MyJedisPool.MASTER_PORT02);
        };
        Runnable r3 = ()->{
            TestJedis.setRedisKV("jedis6381","Test 6381 Jedis",MyJedisPool.MASTER_PORT03);
        };
//        Thread.sleep(1000);
        new Thread(r1).start();
//        Thread.sleep(1000);
//        new Thread(r2).start();
//        Thread.sleep(1000);
//
//        new Thread(r3).start();
    }


    public static void setRedisKV(String key,String val,int port) {
        Jedis jedis = getJedis(port);
//        jedis.set(key,val);
        System.out.printf("Redis Port=%d \t Response=%s\n",port,jedis.ping());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("Redis Key=%s \t Value=%s\n",key,jedis.get(key));

        jedis.close();
    }
    public static Jedis getJedis(int port){
        JedisPool jedisPool = MyJedisPool.getJedisPool(port);
        return jedisPool.getResource();
    }
}
