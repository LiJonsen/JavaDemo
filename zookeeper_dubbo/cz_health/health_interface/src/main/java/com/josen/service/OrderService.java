package com.josen.service;

import com.josen.pojo.Order;
import com.josen.pojo.SubmitOrder;

import java.text.ParseException;
import java.util.Map;

/**
 * @InterfaceName OrderService
 * @Description 提交体检预约套餐业务逻辑
 * @Author Josen
 * @Create 2020/9/11 16:22
 */
public interface OrderService {
    /**
     * 新增体检预约订单
     * @param order
     */
    Order addOrder(Order order);

    /**
     * 校验体检预约订单信息
     * @param submitOrder
     * @return
     */
    Map validateOrder(SubmitOrder submitOrder) throws ParseException;

    /**
     *
     * @param orderId
     * @return
     */
    Map getOrderDetailsById(Integer orderId);
}
