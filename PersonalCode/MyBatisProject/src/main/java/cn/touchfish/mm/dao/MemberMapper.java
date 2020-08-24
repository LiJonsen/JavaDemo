package cn.touchfish.mm.dao;

import cn.touchfish.mm.pojo.WxMember;
import cn.touchfish.wxApi.pojo.WxLogin;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName MemberMapper
 * @Description
 * @Author Josen
 * @Create 2020/8/23 16:52
 */
public interface MemberMapper {
    /**
     * 根据open_id查询微信用户信息
     */
    WxMember queryWxMemberByOpenId(@Param("openId") String openId);

    /**
     * 插入一条微信用户信息到t_wx_member
     */
    void insertOneWeChatMember(WxMember wxMember);

    /**
     * 更新用户学科和城市id
     */
    boolean updateMemberCityAndCourse(WxLogin wxLogin);
}
