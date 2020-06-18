package com.helloSpring;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Customer
 * @Description 用户
 * @Author Josen
 * @Date 2020/6/17 17:04
 * @Version 1.0
 **/
public class Customer {
    private String name;
    private Order order;
    private List<Order> order_list;
    private Map<Integer,Order> order_map;
    public Customer() {
    }

    public Customer(String name, Order order) {
        this.name = name;
        this.order = order;
    }
    public Customer(String name, Order order, List<Order> order_list) {
        this.name = name;
        this.order = order;
        this.order_list = order_list;
    }
    public Customer(String name, Order order, List<Order> order_list, Map<Integer, Order> order_map) {
        this.name = name;
        this.order = order;
        this.order_list = order_list;
        this.order_map = order_map;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", order=" + order +
                ", order_list=" + order_list +
                ", order_map=" + order_map +
                '}';
    }

    public List<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Order> order_list) {
        this.order_list = order_list;
    }

    public Map<Integer, Order> getOrder_map() {
        return order_map;
    }

    public void setOrder_map(Map<Integer, Order> order_map) {
        this.order_map = order_map;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
