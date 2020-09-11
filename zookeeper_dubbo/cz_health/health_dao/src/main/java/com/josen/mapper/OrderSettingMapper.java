package com.josen.mapper;

import com.josen.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName OrderSettingMapper
 * @Description 预约设置Mapper
 * @Author Josen
 * @Create 2020/9/9 17:01
 */
public interface OrderSettingMapper {
    /**
     * 更新
     * @param orderSetting
     */
    void updateOrderSetting(OrderSetting orderSetting);

    /**
     * 新增
     * @param orderSetting
     */
    void insertOrderSetting(OrderSetting orderSetting);

    /**
     * 查询
     * @param orderSetting
     */
    OrderSetting queryOrderSetting(OrderSetting orderSetting);

    /**
     * 根据日期查询
     */
    List<OrderSetting> queryOrderSettingByDate(@Param("start") String start, @Param("end") String end);

    /**
     * 更新指定日期预约人数+1
     * @param orderDate
     */
    void incrOrderSettingReservations(Date orderDate);
}
