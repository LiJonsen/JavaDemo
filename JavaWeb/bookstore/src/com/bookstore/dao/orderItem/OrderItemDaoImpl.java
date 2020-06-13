package com.bookstore.dao.orderItem;

import com.bookstore.dao.BaseDao;
import com.bookstore.pojo.OrderItem;

/**
 * @ClassName OrderItemDaoImpl
 * @Description OrderItemDao接口实现类
 * @Author Josen
 * @Date 2020/6/13 9:55
 * @Version 1.0
 **/
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao{
    @Override
    public int createOrderItems(OrderItem orderItem) {
        System.out.println(" OrderItemDaoImpl 程序在[" +Thread.currentThread().getName() + "]中");

        String sql = "insert into store_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrder_id());
    }
}
