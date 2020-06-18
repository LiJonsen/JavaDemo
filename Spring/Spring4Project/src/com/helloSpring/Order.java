package com.helloSpring;

/**
 * @ClassName Order
 * @Description TODO
 * @Author Josen
 * @Date 2020/6/17 17:02
 * @Version 1.0
 **/
public class Order {
    private String order_id;
    private String order_name;
    private Integer price;

    public Order() {
    }
    public Order(String order_id, String order_name, Integer price) {
        this.order_id = order_id;
        this.order_name = order_name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", order_name='" + order_name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
