package com.josen.entity;

import lombok.Data;

/**
 * @ClassName AccessToken
 * @Description Details
 * @Author Josen
 * @Create 2020/9/5 21:19
 */
@Data
public class AccessToken {
    private String accessToken;
    private Integer expiresIn; // token有效时长
}
