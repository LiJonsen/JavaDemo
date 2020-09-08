package com.josen.pojo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author Josen
 * @Create 2020/9/3 14:20
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String cover_img;
    private String password;
}
