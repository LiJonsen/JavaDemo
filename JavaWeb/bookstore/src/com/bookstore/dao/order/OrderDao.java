package com.bookstore.dao.order;
import com.bookstore.pojo.Order;

/**
 * @ClassName BookDao
 * @Description 定义操作store_order订单数据表的方法封装
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
public interface OrderDao {
    /**
     * 添加订单记录
     * @param order
     * @return
     */
    int createOrder(Order order);
}
