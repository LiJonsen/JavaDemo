package com.bookstore.pojo;
import java.math.BigDecimal;

/**
 * @ClassName OrderItem
 * @Description 订单项JavaBean（详细信息）
 * @Author Josen
 * @Date 2020/6/13 0:12
 * @Version 1.0
 **/
public class OrderItem {
    // 自增长主键id
    private Integer id;
    // 商品名称
    private String name;
    // 商品下单数量
    private Integer count;
    // 商品单价
    private BigDecimal price;
    // 订单总价
    private BigDecimal totalPrice;
    // 订单id
    private String order_id;

    public OrderItem() {
    }
    public OrderItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice, String order_id) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", order_id='" + order_id + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
