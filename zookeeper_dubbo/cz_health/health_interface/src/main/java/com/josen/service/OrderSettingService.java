package com.josen.service;

import com.josen.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName OrderSettingService
 * @Description 预约设置业务逻辑接口
 * @Author Josen
 * @Create 2020/9/9 16:47
 */
public interface OrderSettingService {
    /**
     * 添加或更新预约设置数据
     * @param orderSettings
     */
    void addOrUpdateOrderSetting(List<OrderSetting> orderSettings);

    /**
     * 查询 startDate-月底 预约设置列表
     * @param startDate
     * @return
     * @throws NumberFormatException
     */
    List<Map> queryOrderSettingByDate(String startDate) throws NumberFormatException;

    /**
     * 更新指定日期预约人数+1
     */
    void incrOrderSettingReservations(@Param("orderDate") Date orderDate);

}
