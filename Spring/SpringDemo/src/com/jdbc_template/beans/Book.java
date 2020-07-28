package com.jdbc_template.beans;

/**
 * @ClassName Book
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:15
 */
public class Book {
    private String bid;
    private String bookName;
    private Integer price;

    public Book() {
    }

    public Book(String bid, String bookName, Integer price) {
        this.bid = bid;
        this.bookName = bookName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid='" + bid + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
