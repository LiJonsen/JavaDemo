package com.josen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.josen.mapper.OrderSettingMapper;
import com.josen.pojo.OrderSetting;
import com.josen.service.OrderSettingService;
import com.josen.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * @ClassName OrderSettingServiceImpl
 * @Description 预约设置业务逻辑
 * @Author Josen
 * @Create 2020/9/9 16:48
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Override
    public void addOrUpdateOrderSetting(List<OrderSetting> orderSettings) {
        for (OrderSetting order : orderSettings) {
            // 1. 根据date查询该预约是否已存在
            OrderSetting orderSetting = orderSettingMapper.queryOrderSetting(order);
            // 2. 存在则执行修改操作，否则执行新增操作
            if(orderSetting!=null){
                orderSettingMapper.updateOrderSetting(order);
            }else{
                orderSettingMapper.insertOrderSetting(order);
            }

        }
    }
    @Override
    public List<Map> queryOrderSettingByDate(String startDate) throws NumberFormatException {
        // 1. 当前月start日期=startDate、结束=endDate
        String endDate = DateUtils.getLastDayOfMonth(startDate);
        List<OrderSetting> list = orderSettingMapper.queryOrderSettingByDate(startDate, endDate);
        List<Map> res = new ArrayList();
        // 2. 数据整合
        for (OrderSetting item : list) {
            Map map = new HashMap();
            Date date = item.getOrderDate();
            String day = DateUtils.getDayForDate(DateUtils.parseDate2String(date));
            map.put("date",day);
            map.put("number",item.getNumber());
            map.put("reservations",item.getReservations());
            res.add(map);
        }
        return res;
    }

    @Override
    public void incrOrderSettingReservations(Date orderDate) {
        orderSettingMapper.incrOrderSettingReservations(orderDate);
    }
}
