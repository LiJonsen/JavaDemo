package cn.touchfish.service;

import cn.touchfish.beans.SiteMessage;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @ClassName WebSiteService
 * @Description 站点统计Service
 * @Author Josen
 * @Create 2020/8/13 19:46
 */
public interface WebSiteService {
    /**
     * 获取站点统计数据
     * @return
     */
    SiteMessage getWebSiteMsg() throws SQLException;

    Map<String,Object> getWebSiteMsgByMap();

    void updateRedisWebSiteMsg(String name,String username);

    Map<String, Object> getRedisWebSiteHash();
}
