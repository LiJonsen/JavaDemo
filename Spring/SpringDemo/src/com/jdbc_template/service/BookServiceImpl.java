package com.jdbc_template.service;

import com.jdbc_template.beans.Book;
import com.jdbc_template.dao.BookDao;
import com.jdbc_template.exception.BookStockException;
import com.jdbc_template.exception.UserAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName BookServicce
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:20
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;
    @Override
    public void validationBook(Book book) {
        System.out.println("Service ---> 校验Book数据，调用JDBC操作...");
//        bookDao.addBookToDatabase(book);
        bookDao.namedTemplateForAddBook(book);
    }

    @Transactional(isolation=Isolation.READ_COMMITTED)
    public void validationBuyBook(String username,String bid) {
        // 1. 查询书籍价格
        int price = bookDao.findPriceByBid(bid);
        // 2. 付款，更新用户balance
        bookDao.updateBalance(username,price);
        // 3. 减仓，更新库存
        bookDao.updateStock(bid);
    }
}
