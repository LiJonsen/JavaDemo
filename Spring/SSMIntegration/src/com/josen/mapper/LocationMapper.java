package com.josen.mapper;

import com.josen.beans.Location;

import java.util.List;

/**
 * @InterfaceName LocationMapper
 * @Description 地址定位信息SQL接口映射
 * @Author Josen
 * @Create 2020/8/1 19:15
 */
public interface LocationMapper {
    /**
     * 获取地址信息列表
     * @return
     */
    List<Location> queryLocationList();
}
