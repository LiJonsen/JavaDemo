package cn.touchfish.wxApi.service;

import cn.touchfish.mm.dao.MemberMapper;
import cn.touchfish.mm.pojo.WxMember;
import cn.touchfish.mm.utils.DateUtils;
import cn.touchfish.mm.utils.MybatisUtils;
import cn.touchfish.mm.utils.WxUtil;
import cn.touchfish.wxApi.pojo.WxLogin;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MemberService
 * @Description Details
 * @Author Josen
 * @Create 2020/8/23 16:34
 */
public class MemberService {
    /**
     * 校验小程序登录业务逻辑
     * @param wxLogin
     * @return
     */
    public Map validateWxLogin(WxLogin wxLogin){
        // 1. 根据小程序上传的code和开发者的AppID&AppSecret到微信平台获取openid&session_key
        HashMap result = null;
        SqlSession sqlSession = null;
        try {
            JSONObject wxPlatformMsg = WxUtil.get(wxLogin.getCode());
            sqlSession = MybatisUtils.openSqlSession();
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            // 2. 通过openid判断数据库用户是否已注册
            String openid = (String)wxPlatformMsg.get("openid");
            WxMember userInfo = mapper.queryWxMemberByOpenId(openid);
            // 3 未注册-解密用户数据
            if(userInfo == null){
                // 3.1 - 拿到小程序上传的encryptedData&iv和微信平台获取的session_key，解密获取到用户信息
                String sessionKey = (String)wxPlatformMsg.get("session_key");
                JSONObject newMember = WxUtil.getUserInfo(wxLogin.getEncryptedData(), sessionKey, wxLogin.getIv());
                userInfo = newMember.toJavaObject(WxMember.class);
                userInfo.setCreateTime(DateUtils.parseDate2String(new Date()));

                // 3.2 - 将用户信息插入到数据库
                mapper.insertOneWeChatMember(userInfo);
            }
            // 4. 整合客户端响应数据
            MybatisUtils.commitAndClose(sqlSession);
            result = new HashMap();
            result.put("token",userInfo.getOpenId());
            result.put("userInfo",userInfo);
        } catch (IOException e) {
            MybatisUtils.rollbackAndClose(sqlSession);
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /**
     * 设置微信用户的城市和学科
     */
    public boolean settingCityAndCourse(WxLogin wxLogin) {
        boolean status = false;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSqlSession();
            status = sqlSession.getMapper(MemberMapper.class).updateMemberCityAndCourse(wxLogin);
            MybatisUtils.commitAndClose(sqlSession);
        } catch (IOException e) {
            MybatisUtils.rollbackAndClose(sqlSession);
            e.printStackTrace();
        }
        return status;
    }
}
