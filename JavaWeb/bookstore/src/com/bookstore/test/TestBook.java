package com.bookstore.test;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.service.book.BookServiceImpl;
import com.bookstore.utils.JdbcUtils;
import org.junit.Test;

//import java.sql.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestBook
 * @Description Book功能测试
 * @Author Josen
 * @Date 2020/6/10 14:10
 * @Version 1.0
 **/
public class TestBook {
    private BookServiceImpl bookService = new BookServiceImpl();
    @Test
    public void testBookService(){
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void testBooksPagination(){
        System.out.println("start");
        Page<Book> bookPage = bookService.queryBooks(1, 4);
        System.out.println(bookPage);
    }
    @Test
    public void test2(){
        try {
            Connection druidConn = JdbcUtils.getDruidConn();
            System.out.println(druidConn);
            System.out.println(" test2 程序在[" +Thread.currentThread().getName() + "]中");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
