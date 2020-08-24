package cn.touchfish.wxApi.pojo;

import lombok.Data;

/**
 * @ClassName WxLogin
 * @Description Details
 * @Author Josen
 * @Create 2020/8/23 16:33
 */
@Data
public class WxLogin {
    private String code; //临时登录凭证
    private String encryptedData; //加密后的微信帐号数据
    private String iv; //解密数据需要的偏移量

    // 拓展字段-用于更新指定openId的学科和城市id
    private Integer cityID;
    private Integer subjectID;
    private String openId;

}
