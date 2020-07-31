package com.jdbc_template.service;

import com.jdbc_template.beans.Book;

/**
 * @ClassName BookService
 * @Description Details
 * @Author Josen
 * @Create 2020/7/28 11:21
 */
public interface BookService {
    void validationBook(Book book);

    void validationBuyBook(String username,String bid);
}
