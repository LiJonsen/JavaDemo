package com.josen.mapper;

import com.josen.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @InterfaceName OrderMapper
 * @Description 体检预约订单Mapper
 * @Author Josen
 * @Create 2020/9/11 19:55
 */
public interface OrderMapper {
    /**
     * 根据setmealId和memberId查询预约订单
     * @param setmealId 套餐ID
     * @param memberId 会员ID
     * @return
     */
    Order queryOrderByMidAndSid(@Param("setmealId") Integer setmealId,@Param("memberId") Integer memberId);


    /**
     * 新增体检预约订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 根据OrderId查询体检预约成功信息
     * @param orderId
     * @return
     */
    Map getOrderDetailsById(@Param("orderId") Integer orderId);
}
