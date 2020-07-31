package com.jdbc_template;

import com.jdbc_template.beans.Book;
import com.jdbc_template.controller.BookController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestTransaction
 * @Description Spring的事务管理
 * @Author Josen
 * @Create 2020/7/28 14:46
 */
public class TestTransaction {
    private BookController bookController;
    @Before
    public void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-transaction-config.xml");

        bookController = context.getBean("bookController", BookController.class);
    }

    @Test
    public void testBuyBook(){
        bookController.buyBook("Tom","1001");
    }
}
