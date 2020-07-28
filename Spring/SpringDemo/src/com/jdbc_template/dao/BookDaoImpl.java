package com.jdbc_template.dao;

import com.jdbc_template.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName BookDaoImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:33
 */
@Repository
public class BookDaoImpl implements BookDao{
    @Autowired
    private JdbcTemplate jdbc;
    @Override
    public void addBookToDatabase(Book book) {
        String sql = "INSERT INTO book(bid,book_name,price) values(?,?,?)";
        int update = jdbc.update(sql, book.getBid(), book.getBookName(), book.getPrice());
        System.out.printf("Repository ---> 更新了 %d 条数据... \n",update);
    }
}
