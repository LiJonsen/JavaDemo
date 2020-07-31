package com.jdbc_template;

import com.jdbc_template.beans.Book;
import com.jdbc_template.controller.BookController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName OperationBook
 * @Description 测试jdbcTemplate
 * @Author Josen
 * @Create 2020/7/28 11:18
 */
public class OperationBook {
    private BookController bookController;
    @Before
    public void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jdbc-config.xml");

        bookController = context.getBean("bookController", BookController.class);
    }
    @Test
    public void testAddBook() {
        bookController.addBook(new Book("1004","幸福人生",39));
    }

}
