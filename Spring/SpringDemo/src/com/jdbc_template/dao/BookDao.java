package com.jdbc_template.dao;

import com.jdbc_template.beans.Book;

/**
 * @InterfaceName BookDao
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:33
 */
public interface BookDao {
    void addBookToDatabase(Book book);
}
