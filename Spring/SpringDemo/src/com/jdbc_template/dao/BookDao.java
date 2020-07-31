package com.jdbc_template.dao;

import com.jdbc_template.beans.Book;

/**
 * @InterfaceName BookDao
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:33
 */
public interface BookDao {
    /**
     * 使用jdbcTemplate
     * @param book
     */
    void addBookToDatabase(Book book);

    /**
     * 使用namedTemplate具名参数
     * @param book
     */
    void namedTemplateForAddBook(Book book);

    /**
     * 根据bid查询书籍价格
     * @param bid
     * @return
     */
    int findPriceByBid(String bid);

    /**
     * 更新库存
     * @param bid
     */
    void updateStock(String bid);

    /**
     * 更新余额
     * @param username
     * @param price
     */
    void updateBalance(String username,Integer price);
}
