package com.bookstore.pojo;

import java.math.BigDecimal;

/**
 * @ClassName Book
 * @Description 对应books数据表中一条记录
 * @Author Josen
 * @Date 2020/6/10 13:15
 * @Version 1.0
 **/
public class Book {
    // 唯一标识
    private int id;
    // 名称
    private String name;
    // 价格
    private BigDecimal price;
    // 作者
    private String author;
    // 销售量
    private int sales;
    // 股票
    private Integer stock;
    // 书籍图片路径
    private String img_path;

    public Book() {
    }

    public Book(int id, String name, BigDecimal price, String author, int sales, Integer stock, String img_path) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        if(img_path!=null && !"".equals(img_path)){
            this.img_path = img_path;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock='" + stock + '\'' +
                ", img_path='" + img_path + '\'' +
                '}';
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
