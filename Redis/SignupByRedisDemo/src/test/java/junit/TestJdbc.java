package junit;

import cn.touchfish.beans.CountForUser;
import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.SiteMessage;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.service.WebSiteService;
import cn.touchfish.service.WebSiteServiceImpl;
import cn.touchfish.utils.DecimalProxy;
import cn.touchfish.utils.Jedis.MyJedisCmdImpl;
import cn.touchfish.utils.Jedis.MyJedisCommands;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.MapperUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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

//        JedisUtils.getJedisCmd().ex_set("k1", "v1");
//        Map<String,String> map = new HashMap<>();
//        map.put("k1","c_v1");
//        map.put("k2","c_v2");
//        JedisUtils.getJedisCmd().ex_hmset("testMap",map);
    }

    @Test
    public void TestCountMsg() {
//        Set<String> login_keys = JedisUtils.getJedisCmd().ex_hkeys(WebSiteServiceImpl.LOGIN_CACHE_KEY);
//        // 数据整合
//        Map<String,HomeUser> userMap = new HashMap<>();
//        HomeUser user;
//
//        // 1. 获取Redis缓存信息
//        for (String account : login_keys) {
//            user = new HomeUser();
//            String l_val = JedisUtils.getJedisCmd().ex_hget(WebSiteServiceImpl.LOGIN_CACHE_KEY, account);
//            String a_val = JedisUtils.getJedisCmd().ex_hget(WebSiteServiceImpl.ACCESS_CACHE_KEY, account);
//            user.setLoginCount(Integer.valueOf(l_val));
//            user.setAccessCount(Integer.valueOf(a_val));
//            user.setUsername(account);
////            users.add(user);
//            userMap.put(account,user);
//        }
//        // 2. 获取Mysql数据
//        List<CountForUser> countForUsers = MapperUtil.getSqlMapper(UserMapper.class).queryUserForCount();
//        List<HomeUser> users = new ArrayList<>();
//
//
//        // 3. 对比数据，提取变更的数据
//        for (CountForUser countForUser : countForUsers) {
//            String str = countForUser.getUsername();
//            HomeUser homeUser = userMap.get(str);
//            if(homeUser.getAccessCount() == countForUser.getAccessCount() && homeUser.getLoginCount() == countForUser.getLoginCount()){
//                continue;
//            }
//            users.add(homeUser);
//        }
//
//        // 4. 提交Sql
//        System.out.println("users="+users);
    }

    @Test
    public void TestSiteMsg()  {
//        Set<String> login_keys = JedisUtils.getJedisCmd().ex_hkeys(WebSiteServiceImpl.LOGIN_CACHE_KEY);
//        Set<String> access_keys = JedisUtils.getJedisCmd().ex_hkeys(WebSiteServiceImpl.ACCESS_CACHE_KEY);
//        String[] strings = access_keys.toArray(new String[access_keys.size()]);
//        List<String> list = JedisUtils.getJedisCmd().ex_hmget(WebSiteServiceImpl.ACCESS_CACHE_KEY, strings);
//        System.out.println(list);
    }

    @Test
    public void TestUserPage(){
//        List<HomeUser> users = MapperUtil.getSqlMapper(UserMapper.class).queryUserList(0,10);
//        System.out.println(users);
//        System.out.println(users.size());
    }

}
