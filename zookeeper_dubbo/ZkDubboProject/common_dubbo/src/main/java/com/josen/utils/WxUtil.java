package com.josen.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.josen.entity.AccessToken;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具类
 */
public class WxUtil {
    // 从小程序管理后台获取AppId及secret
    private final static  String appid = "wx63e3f12d18572135";
    private final static  String secret = "23afc0c26a1739990eaa4c62929fe4e3";
    public final static String access_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 请求CODE
    public final static String CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid="+appid+"&redirect_uri=https://touchfish.cn&response_type=code&scope=snsapi_login&state=123#wechat_redirect";
    /**
     * 获取Code
     * @return
     */
    public static String getCode(){
        System.out.println(CODE_URL);
        return HttpUtil.httpGet(CODE_URL);
    }

    public static String getScanUrl(){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri=https%3a%2f%2ftouchfish.cn%2f&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
        return url;
    }

    /**
     * 根据APPID+APPSECRET获取access_token
     * 注意：默认有效期2小时
     * @return
     */
    public static AccessToken getAccessToken() {
        //替换真实appid和appsecret
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", secret);
        AccessToken accesstoken=new AccessToken();
        //得到json对象
        JSONObject jsonObject = JSON.parseObject(HttpUtil.httpGet(requestUrl));
        Integer  expires_in = Integer.parseInt(jsonObject.getString("expires_in"));

        //将得到的json对象的属性值，存到accesstoken中
        accesstoken.setAccessToken(jsonObject.getString("access_token"));
        accesstoken.setExpiresIn(expires_in);
        return accesstoken;
    }

    /**
     * 根据code获取openid、session_key
     * @param js_code
     * @return
     * @throws RuntimeException
     */
    public static JSONObject get(String js_code) throws RuntimeException {
        //可获取openid及session_key,其实这里openid不需要获取，encryptedData解密后包含openid
        //官方接口，需要自己提供appid，secret和js_code
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code + "&grant_type=authorization_code";
        //HttpRequestor是一个网络请求工具类
        String result = HttpUtil.httpGet(requestUrl);
        return JSON.parseObject(result);
    }
    // 获取微信用户信息
    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
