package cn.touchfish.listener;
import cn.touchfish.beans.HomeUser;
import cn.touchfish.mapper.HomeMapper;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.service.WebSiteService;
import cn.touchfish.service.WebSiteServiceImpl;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.MapperUtil;
import cn.touchfish.utils.MyC3p0Pool;
import cn.touchfish.utils.MyJedisPool;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ServletInitListener
 * @Description 对Servlet整个上下文进行监听（创建、销毁）。
 * @Author Josen
 * @Create 2020/8/14 10:07
 */
public class ServletInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletInitListener: Servlet Init...");
        // 初始化C3P0连接池
        MyC3p0Pool.getDataSource();

        // 初始化Redis连接池
        MyJedisPool.getJedisPool();

        // 初始化WebSite统计数据到Redis缓存
        initRedisWebSiteMsg();
        // 初始化将Mysql中用户列表login_count&access_count同步到Redis缓存
        initLoginAndAccessCache();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletInitListener: Servlet Destroyed...");
        // 关闭Redis连接池
        JedisPool jedisPool = MyJedisPool.getJedisPool();
        if (jedisPool != null) {
            jedisPool.close();
        }

        // 关闭C3P0连接池
        ComboPooledDataSource dataSource = MyC3p0Pool.getDataSource();
        if (dataSource != null) {
            dataSource.close();
        }
    }

    /**
     * 初始化将Mysql的统计数据同步到Redis
     */
    private void initRedisWebSiteMsg() {
        Runnable runnable = () -> {
            Map<String, Object> msg = MapperUtil.getSqlMapper(HomeMapper.class).getSiteMessage();
            Map<String,String> str_msg = new HashMap<>();
            // 将Map集合中的Long转换成String
            msg.forEach((key,val)->str_msg.put(key,val.toString()));
            // 初始化网站统计缓存
//            System.out.println("str_msg="+str_msg);
            JedisUtils.getJedisCmd().ex_hmset(WebSiteServiceImpl.WEB_SITE_KEY,str_msg);
        };
        // 执行线程
        Thread thread = new Thread(runnable);
        thread.setName("Thread-InitRedis");
        thread.start();
    }

    /**
     * 初始化将Mysql的login_count&access_count同步到Redis缓存
     * 【将用户列表中所有login_count & access_count数据缓存起来】
     */
    private void initLoginAndAccessCache(){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                List<HomeUser> userList = MapperUtil.getSqlMapper(UserMapper.class).queryUserList();
//                System.out.println("userList="+userList);
                for (HomeUser user : userList) {
                    String username = user.getUsername();
                    String loginCount = ""+user.getLoginCount();
                    String accessCount = ""+user.getAccessCount();
                    JedisUtils.getJedisCmd().ex_hset(WebSiteServiceImpl.LOGIN_CACHE_KEY,username,loginCount);
                    JedisUtils.getJedisCmd().ex_hset(WebSiteServiceImpl.ACCESS_CACHE_KEY,username,accessCount);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("Thread-InitUserListCache");
        thread.start();
    }
}
