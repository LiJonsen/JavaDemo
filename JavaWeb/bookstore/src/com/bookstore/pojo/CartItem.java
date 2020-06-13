package com.bookstore.pojo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName CartItem
 * @Description 购物车商品项JavaBean（商品信息）
 * @Author Josen
 * @Date 2020/6/12 13:39
 * @Version 1.0
 **/
public class CartItem {
    // 商品ID
    private int id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    public CartItem() {
    }
    public CartItem(int id, String name, Integer count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = price.multiply(new BigDecimal(count));
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id &&
                Objects.equals(name, cartItem.name) &&
                Objects.equals(count, cartItem.count) &&
                Objects.equals(price, cartItem.price) &&
                Objects.equals(totalPrice, cartItem.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, price, totalPrice);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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



}
