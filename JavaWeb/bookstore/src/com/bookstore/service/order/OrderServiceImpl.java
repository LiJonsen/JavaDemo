package com.bookstore.service.order;
import com.bookstore.dao.book.BookDao;
import com.bookstore.dao.book.BookDaoImpl;
import com.bookstore.dao.order.OrderDao;
import com.bookstore.dao.order.OrderDaoImpl;
import com.bookstore.dao.orderItem.OrderItemDao;
import com.bookstore.dao.orderItem.OrderItemDaoImpl;
import com.bookstore.pojo.*;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description OrderService接口实现类
 * @Author Josen
 * @Date 2020/6/13 10:03
 * @Version 1.0
 **/
public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, int userId) {
        // 1. 在store_order数据表新增一条订单记录
        long cur_time = System.currentTimeMillis();
        String order_id = cur_time + "" + userId;
        Order order = new Order(order_id, new Date(), Order.UNTREATED, userId);
        int res = orderDao.createOrder(order);
        // 测试事务回滚
        Order test = null;
        System.out.println(test.getUser_id());

        // 2. 在store_order_item数据表新增一或多条订单项（即购物车商品）
        if(res>0){
            for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
                CartItem v = entry.getValue();
                orderItemDao.createOrderItems(new OrderItem(null,v.getName(),v.getCount(),v.getPrice(),v.getTotalPrice(),order_id));

                // 更新books库存和销量
                Book book = bookDao.queryBookById(v.getId());
                book.setSales(book.getSales()+1);
                book.setStock(book.getStock()-1);
                bookDao.updateBook(book);
            }

            // 清空购物车
            cart.clearCart();
            return order_id;
        }
        return null;
    }
}
