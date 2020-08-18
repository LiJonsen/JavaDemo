package cn.touchfish.controller;

import cn.touchfish.beans.HomeUser;
import cn.touchfish.beans.Result;
import cn.touchfish.beans.SiteMessage;
import cn.touchfish.beans.User;
import cn.touchfish.mapper.UserMapper;
import cn.touchfish.service.UserService;
import cn.touchfish.service.UserServiceImpl;
import cn.touchfish.service.WebSiteService;
import cn.touchfish.service.WebSiteServiceImpl;

import cn.touchfish.utils.JedisUtils;
import cn.touchfish.utils.MapperUtil;
import cn.touchfish.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName User
 * @Description 用户相关接口
 * @Author Josen
 * @Create 2020/8/13 17:31
 */
@WebServlet("/home")
public class Home extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private WebSiteService webSiteService = new WebSiteServiceImpl();

    /**
     * 获取用户列表接口
     *
     * @param req
     * @param resp
     * @param params
     * @throws IOException
     */
    protected void getUserList(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> params) throws IOException {
        Result res = new Result(500, "response fail!", "");
        String username = (String)params.get("username");
        int current = (Integer)params.get("current");
        int pageSize = (Integer)params.get("pageSize");
        List<HomeUser> list = userService.getUserListByPage(current, pageSize);
        long total = MapperUtil.getSqlMapper(UserMapper.class).queryUserTotal();
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("total",total);
        res.setCode(200);
        res.setMsg("response success!");
        res.setData(resMap);
        webSiteService.updateRedisWebSiteMsg("access_count", username);

        ServletUtils.executeResponseJSON(resp, res);
    }

    /**
     * 获取站点统计信息接口（首页饼状图）
     *
     * @param req
     * @param resp
     * @param params
     * @throws IOException
     */
    protected void getSiteMessage(HttpServletRequest req, HttpServletResponse resp, Map<String, String> params) throws IOException {
        Result res = new Result(500, "response fail!", "");
//        try {
//            SiteMessage webSiteMsg = webSiteService.getWebSiteMsg();
        Map msg = webSiteService.getWebSiteMsgByMap();
        res.setCode(200);
        res.setMsg("response success!");
        res.setData(msg);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ServletUtils.executeResponseJSON(resp, res);
    }
}
