package com.bookstore.dao.orderItem;
import com.bookstore.pojo.OrderItem;
/**
 * @ClassName BookDao
 * @Description 定义操作store_order_item订单项数据表的方法封装
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
public interface OrderItemDao {
    /**
     * 添加订单项数据到数据库表
     * @param orderItem
     * @return
     */
    int createOrderItems(OrderItem orderItem);
}
