package cn.touchfish.service;

import cn.touchfish.beans.CountForUser;
import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.SiteMessage;
import cn.touchfish.dao.WebSiteDao;
import cn.touchfish.dao.WebSiteDaoImpl;
import cn.touchfish.mapper.HomeMapper;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.utils.JdbcUtils;
import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.MapperUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName WebSiteServiceImpl
 * @Description 网站统计信息接口实现类
 * @Author Josen
 * @Create 2020/8/13 19:47
 */
public class WebSiteServiceImpl implements WebSiteService {

    private WebSiteDao webSiteDao = new WebSiteDaoImpl();
    // Login&Access统计缓存Key
    public static final String LOGIN_CACHE_KEY = "LoginWebSiteCacheInfo";
    public static final String ACCESS_CACHE_KEY = "AccessWebSiteCacheInfo";
    // 缓存计数器-当Login/Access更新次数>10，执行同步Mysql对应用户的数据
    private static int cacheUpdateCount = 1;
    // 网站统计信息Redis缓存Key值
    public static final String WEB_SITE_KEY = "WebSiteMsg";

    // Old - dbUtils
    @Override
    public SiteMessage getWebSiteMsg() throws SQLException {
        return webSiteDao.queryWebSiteTotal();
    }

    // New - Mybatis
    @Override
    public Map<String, Object> getWebSiteMsgByMap() {
        Map<String, Object> webSiteHash = getRedisWebSiteHash();
        // 优先从缓存获取统计信息
        if (webSiteHash.size() > 0) {
            return webSiteHash;
        }
        Map<String, Object> res = MapperUtil.getSqlMapper(HomeMapper.class).getSiteMessage();
        MapperUtil.closeSqlSession();
        return res;
    }

    /**
     * 获取SiteMessage所有属性名
     *
     * @return
     */
    public String[] getWebSiteBeanFields() {
        Field[] fields = SiteMessage.class.getDeclaredFields();
        String[] names = new String[fields.length];
        int index = 0;
        for (Field field : fields) {
            String name = field.getName();
            names[index] = name;
            index++;
        }
        return names;
    }

    /**
     * 更新Redis 统计数据
     * @param name field name
     */
    public void updateRedisWebSiteMsg(String name, String username) {
        Map<String, Object> webSiteHash = getRedisWebSiteHash();
        for (Map.Entry<String, Object> entry : webSiteHash.entrySet()) {
            String key = entry.getKey();
            if (key.equals(name)) {
                // 更新登录&访问统计缓存数据
                updateLoginAndAccessInfo(name, username);
//                    System.out.printf("update redis WebSite Key=%s Value=%s\n",name,num.toString());
                JedisUtils.getJedisCmd().ex_hincrBy(WEB_SITE_KEY, name, 1);
                return;
            }
        }
    }

    /**
     * 更新登录&访问统计缓存数据
     * @param name
     * @param username
     */
    private void updateLoginAndAccessInfo(String name, String username) {
        if ("login_count".equals(name)) {
            JedisUtils.getJedisCmd().ex_hincrBy(LOGIN_CACHE_KEY, username, 1);
        } else if ("access_count".equals(name)) {
            JedisUtils.getJedisCmd().ex_hincrBy(ACCESS_CACHE_KEY, username, 1);
        }
        // 判断Redis缓存同步数据到Mysql
        if ("login_count".equals(name) || "access_count".equals(name)) {
//            System.out.println("cacheUpdateCount="+cacheUpdateCount);
            if (cacheUpdateCount >= 100) {
                // 重置更新计数
                cacheUpdateCount = 1;
                updateMysqlCountForUser();
                return;
            }
            cacheUpdateCount++;
        }
    }

    /**
     * 开启一条线程去同步Redis缓存到Mysql
     */
    private void updateMysqlCountForUser() {
        Runnable runnable = () -> {
            // 数据整合
            Set<String> login_keys = JedisUtils.getJedisCmd().ex_hkeys(WebSiteServiceImpl.LOGIN_CACHE_KEY);
            Map<String, HomeUser> userMap = new HashMap<>();
            HomeUser user;
            // 1. 获取Redis缓存信息
            for (String account : login_keys) {
                user = new HomeUser();
                String l_val = JedisUtils.getJedisCmd().ex_hget(WebSiteServiceImpl.LOGIN_CACHE_KEY, account);
                String a_val = JedisUtils.getJedisCmd().ex_hget(WebSiteServiceImpl.ACCESS_CACHE_KEY, account);
                user.setLoginCount(Integer.valueOf(l_val));
                user.setAccessCount(Integer.valueOf(a_val));
                user.setUsername(account);
                userMap.put(account, user);
            }
            // 2. 获取Mysql数据
            List<CountForUser> countForUsers = MapperUtil.getSqlMapper(UserMapper.class).queryUserForCount();
            List<HomeUser> users = new ArrayList<>();
            MapperUtil.closeSqlSession();

            // 3. 对比数据，提取变更的数据
            for (CountForUser countForUser : countForUsers) {
                String str = countForUser.getUsername();
                HomeUser homeUser = userMap.get(str);
                if (homeUser.getAccessCount() == countForUser.getAccessCount() && homeUser.getLoginCount() == countForUser.getLoginCount()) {
                    continue;
                }
                users.add(homeUser);
            }

            // 4. 提交Sql
            if(users.size()>0){
                int i = MapperUtil.getSqlMapper(HomeMapper.class).updateCountByUsers(users);
//                System.out.println("mysql update cache count="+i);
                // 批量提交更新
                MapperUtil.commitTransaction();
                MapperUtil.closeSqlSession();
            }
        };

        new Thread(runnable).start();
    }

    /**
     * 获取网站统计Redis缓存信息
     *
     * @return
     */
    public Map<String, Object> getRedisWebSiteHash() {
        String[] names = getWebSiteBeanFields();
        List<String> list = JedisUtils.getJedisCmd().ex_hmget(WEB_SITE_KEY, names);
        Map<String, Object> message = new HashMap<>();
        if (list.size() > 0) {
            int index = 0;
            //返回缓存信息
            for (String name : names) {
                message.put(name, list.get(index));
                index++;
            }
        }
        return message;
    }
}
