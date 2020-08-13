package com.company;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        Jedis jedis = new Jedis("42.194.197.225", 6382);
//        Jedis jedis = new Jedis("106.53.88.252", 6379);
        Jedis jedis = new Jedis("www.touchfish.cn", 6379);
//        jedis.auth("root");
        System.out.println(jedis.ping());

        jedis.set("test","Hello Redis...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("test"));

        jedis.close();

    }
}
