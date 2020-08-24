package cn.touchfish.wxApi.pojo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Location
 * @Description Details
 * @Author Josen
 * @Create 2020/8/23 12:13
 */
@Data
public class Location {
    /**
     * fs=0：不首页显示 - 所有城市
     * fs=1：首页显示-热门城市
     */
    private Integer fs;

    private String location;// 小程序定位的经纬度
}
