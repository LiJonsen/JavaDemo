package com.bookstore.service.order;

import com.bookstore.pojo.Cart;

/**
 * @ClassName BookDao
 * @Description 为Web层提供操作OrderDao和OrderItemDao的方法
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
public interface OrderService {
    /**
     * 创建一条订单
     * @param cart
     * @param userId
     * @return 返回order_id
     */
    String createOrder(Cart cart, int userId);
}
