package cn.touchfish.utils.Jedis;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName JedisUtils
 * @Description Jedis工具接口实现类
 * @Author Josen
 * @Create 2020/8/11 11:12
 */
public class MyJedisCmdImpl implements MyJedisCommands{
    private Jedis jedis;
    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
    @Override
    public void ex_setex(String key, int second, String val) {
        jedis.setex(key,second,val);
    }

    @Override
    public void ex_set(String key, String val) {
        jedis.set(key,val);
    }

    @Override
    public void ex_del(String key) {
        jedis.del(key);
    }

    @Override
    public String ex_get(String key) {
        return jedis.get(key);
    }

    @Override
    public void ex_hmset(String key, Map<String, String> map) {
        jedis.hmset(key, map);
    }

    @Override
    public void ex_hset(String key, String field,String val) {
        jedis.hset(key,field,val);
    }

    @Override
    public String ex_hget(String key, String field) {
        return jedis.hget(key,field);
    }

    /**
     * 获取redis hash
     * @param key hash的key值
     * @param names 多个Key值 - Map<Key,Value>
     * @return
     */
    @Override
    public List<String> ex_hmget(String key,String[] names) {
        return jedis.hmget(key,names);
    }


    @Override
    public void ex_hincrBy(String key, String field, long val) {
        jedis.hincrBy(key,field,val);
    }

    @Override
    public Set<String> ex_hkeys(String key) {
        return jedis.hkeys(key);
    }

}
