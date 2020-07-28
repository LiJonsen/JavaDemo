package com.jdbc_template.service;

import com.jdbc_template.beans.Book;
import com.jdbc_template.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        bookDao.addBookToDatabase(book);
    }
}
