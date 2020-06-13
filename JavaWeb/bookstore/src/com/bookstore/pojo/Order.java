package com.bookstore.pojo;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单JavaBean
 * @Author Josen
 * @Date 2020/6/13 0:12
 * @Version 1.0
 **/
public class Order {
    // 未发货
    public static final int UNTREATED = 0;
    // 已发货
    public static final int PROCESSED = 1;
    // 已完成
    public static final int DONE = 2;
    // 订单id
    private String order_id;
    // 订单创建时间
    private Date create_time;
    // 发货状态
    private int status = UNTREATED;
    // 外链用户表id
    private int user_id;

    public Order() {
    }

    public Order(String order_id, Date create_time, int status, int user_id) {
        this.order_id = order_id;
        this.create_time = create_time;
        this.status = status;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", create_time=" + create_time +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
