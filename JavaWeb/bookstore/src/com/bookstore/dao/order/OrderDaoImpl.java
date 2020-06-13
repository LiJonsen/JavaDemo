package com.bookstore.dao.order;

import com.bookstore.dao.BaseDao;
import com.bookstore.pojo.Order;

/**
 * @ClassName OrderDaoImpl
 * @Description OrderDao接口实现类
 * @Author Josen
 * @Date 2020/6/13 9:45
 * @Version 1.0
 **/
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao{
    @Override
    public int createOrder(Order order) {
        System.out.println(" OrderDaoImpl 程序在[" +Thread.currentThread().getName() + "]中");

        String sql = "insert into store_order(`order_id`,`create_time`,`status`,`user_id`) values(?,?,?,?)";
        return update(sql,order.getOrder_id(),order.getCreate_time(),order.getStatus(),order.getUser_id());
    }
}
