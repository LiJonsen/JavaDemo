package cn.touchfish.dao;

import cn.touchfish.beans.SiteMessage;

import java.sql.SQLException;

/**
 * @InterfaceName WebSiteDao
 * @Description 网站统计Dao
 * @Author Josen
 * @Create 2020/8/13 18:36
 */
public interface WebSiteDao {
    /**
     * 查询网站饼状图统计数据
     * @return
     */
    SiteMessage queryWebSiteTotal() throws SQLException;
}
