package cn.touchfish.wxApi.controller;

import cn.touchfish.mm.constants.Constants;
import cn.touchfish.mm.entity.Result;
import cn.touchfish.mm.framework.annotation.Controller;
import cn.touchfish.mm.framework.annotation.RequestMapping;
import cn.touchfish.mm.pojo.WxMember;
import cn.touchfish.mm.utils.JsonUtils;
import cn.touchfish.wxApi.pojo.WxLogin;
import cn.touchfish.wxApi.service.MemberService;
import cn.touchfish.wxApi.utils.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName MemberController
 * @Description Details
 * @Author Josen
 * @Create 2020/8/23 16:17
 */
@Controller
public class MemberController {
    private MemberService memberService = new MemberService();
    /**
     * 处理小程序设置城市和学科请求
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/wxapi/member/setCityAndCourse")
    public void handlerSetCityAndCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        WxLogin wxLogin = JsonUtils.parseJSON2Object(req, WxLogin.class);
        String authorization = ServletUtil.getReqHeaderToken(req);
        if(authorization!=null){
            wxLogin.setOpenId(authorization);
            boolean status = memberService.settingCityAndCourse(wxLogin);
            if(status){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                JsonUtils.printResult(resp,result);
                return;
            }
        }
        JsonUtils.printResult(resp,result);
    }

    /**
     * 处理微信小程序登录请求,响应微信用户信息
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/wxapi/member/login")
    public void handlerWeChatLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result result = new Result(500);
        try {
            WxLogin wxLogin = JsonUtils.parseJSON2Object(req, WxLogin.class);
            Map message = memberService.validateWxLogin(wxLogin);
            if(message!=null){
                result.setCode(200);
                result.setMessage(Constants.SERVICE_SUCCESS);
                result.setResult(message);
                JsonUtils.printResult(resp,result);
                return;
            }
            result.setMessage("获取用户信息失败！");
            JsonUtils.printResult(resp,result);
        } catch (IOException e) {
            JsonUtils.printResult(resp,result);
            e.printStackTrace();
        }
    }
}
