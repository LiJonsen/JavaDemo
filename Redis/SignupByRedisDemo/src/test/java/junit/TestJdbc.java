package junit;

import cn.touchfish.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.sql.SQLException;
import java.util.*;


/**
 * @ClassName TestJdbc
 * @Description Details
 * @Author Josen
 * @Create 2020/8/11 12:39
 */
public class TestJdbc {
    @Test
    public void testing() throws SQLException {
        Jedis jedis = JedisUtils.getJedis();
        System.out.println(jedis);
//        Map<String,String> map = new HashMap<>();
//        map.put("k1","c_v1");
//        map.put("k2","c_v2");
//        JedisUtils.getJedisCmd().ex_hmset("testMap",map);
    }

}
