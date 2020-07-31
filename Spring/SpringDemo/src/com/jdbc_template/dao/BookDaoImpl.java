package com.jdbc_template.dao;

import com.jdbc_template.beans.Book;
import com.jdbc_template.exception.BookStockException;
import com.jdbc_template.exception.UserAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName BookDaoImpl
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:33
 */
@Repository
public class BookDaoImpl implements BookDao{
    // jdbcTemplate
    @Autowired
    private JdbcTemplate jdbc;

    // namedTemplate
    @Autowired
    private NamedParameterJdbcTemplate npjt;

    /**
     * 使用jdbcTemplate插入book
     * @param book
     */
    @Override
    public void addBookToDatabase(Book book) {
        String sql = "INSERT INTO book(bid,book_name,price) values(?,?,?)";
        int update = jdbc.update(sql, book.getBid(), book.getBookName(), book.getPrice());
        System.out.printf("Repository ---> 更新了 %d 条数据... \n",update);
    }

    /**
     * 使用具名参数插入book
     * @param book
     */
    @Override
    public void namedTemplateForAddBook(Book book) {
        System.out.println("使用具名参数方式：");
        String sql = "INSERT INTO book(bid,book_name,price) values(:bid,:bookName,:price)";
        BeanPropertySqlParameterSource bpsps = new BeanPropertySqlParameterSource(book);
        int update = npjt.update(sql, bpsps);
        System.out.printf("Repository ---> 更新了 %d 条数据... \n",update);
    }

    @Override
    public int findPriceByBid(String bid) {
        String sql = "SELECT price FROM book WHERE bid=?";
        return jdbc.queryForObject(sql, Integer.class, bid);
    }

    @Override
    public void updateStock(String bid) {
        // 判断库存
        String sql = "SELECT stock FROM book_stock WHERE bid=?";
        int stock = jdbc.queryForObject(sql, Integer.class, bid);
        if(stock<=0){
            throw new BookStockException("id="+bid+"的书籍已经没有库存了...");
        }

        String sql2 = "UPDATE book_stock SET stock=stock -1 WHERE bid=?";
        int update = jdbc.update(sql2, bid);
        showUpdateMessage(update,"更新书籍库存完成...");
    }

    @Override
    public void updateBalance(String username, Integer price) {
        // 2. 查询account表username的余额
        String sql = "SELECT balance FROM account WHERE username=?";
        Integer balance = jdbc.queryForObject(sql, Integer.class, username);
        if(price.compareTo(balance)>0){
            // 余额不足
            throw new UserAccountException(username+"用户余额不足...");
        }
        int num = balance-price;
        // 3. 找到account表username的balance，更新余额
        String sql2 = "UPDATE account SET balance=? WHERE username=?";
        int update = jdbc.update(sql2, num, username);

        showUpdateMessage(update,"更新 "+username+" 用户余额完成...");
    }

    private void showUpdateMessage(int update,String msg){
        if(update>0)
            System.out.println(msg);
    }
}
