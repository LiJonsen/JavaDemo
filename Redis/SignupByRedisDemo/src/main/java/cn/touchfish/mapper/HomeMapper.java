package cn.touchfish.mapper;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.SiteMessage;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName HomeMapper
 * @Description Details
 * @Author Josen
 * @Create 2020/8/15 16:22
 */
public interface HomeMapper {
    // 获取站点统计信息
    Map<String,Object> getSiteMessage();

    /**
     * 批量更新t_user表中的login_count & access_count
     * @param homeUser
     * @return
     */
    int updateCountByUsers(List<HomeUser> homeUser);
}
