package cn.touchfish.mm.utils.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @InterfaceName MyJedisCommands
 * @Description 自定义Jedis命令工具类
 * @Author Josen
 * @Create 2020/8/14 17:12
 */
public interface MyJedisCommands {
    /**
     * 添加一对key-value，并添加过期时间
     * @param key
     * @param second
     * @param val
     */
     void ex_setex(String key, int second, String val);
     // 设置键值对
     void ex_set(String key, String val);
     /**
     * 删除key
     * @param key
     */
     void ex_del(String key);
     /**
     * 获取key-value
     * @param key
     * @return
     */
     String ex_get(String key);

 /**
  * 存储map集合
  * @param key
  * @param map
  */
 void ex_hmset(String key, Map<String, String> map);

 /**
  * 设置单个hash值
  * @param key
  * @param field
  * @param val
  */
 void ex_hset(String key, String field, String val);

 /**
  * 删除hash中指定的key值
  */
 void ex_hdel(String key,String field);
 /**
  * 获取单个hash field值
  * @param key
  * @param field
  * @return
  */
 String ex_hget(String key, String field);

 /**
  * 获取map集合
  * @param key
  * @return
  */
 List<String> ex_hmget(String key, String... args);

 /**
  * 对hash集合的某个key进行递增
  * @param key
  * @param field
  * @param val
  */
 void ex_hincrBy(String key, String field, long val);

 /**
  * 获取某个hash下所有的key
  * @param key
  */
 Set<String> ex_hkeys(String key);
}
