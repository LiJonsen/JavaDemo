package cn.touchfish.dao;

import cn.touchfish.beans.SiteMessage;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @ClassName WebSiteDaoImpl
 * @Description
 * @Author Josen
 * @Create 2020/8/13 18:36
 */
public class WebSiteDaoImpl extends BaseDao<SiteMessage> implements WebSiteDao {

    @Override
    public SiteMessage queryWebSiteTotal() throws SQLException {
        // 站点数据统计Sql
        String active_sql = "SELECT COUNT(uid) FROM t_user WHERE is_active=1";
        String user_sql = "SELECT COUNT(uid) FROM t_user";
        String login_sql = "SELECT SUM(login_count) FROM t_user";
        String access_sql = "SELECT SUM(access_count) FROM t_user";
        Long user_count = scalarQuery(user_sql);
        Long active_count = scalarQuery(active_sql);
        BigDecimal access_count = scalarQuery(access_sql);
        BigDecimal login_count = scalarQuery(login_sql);
        return new SiteMessage(access_count, login_count, user_count, active_count);
    }
}
