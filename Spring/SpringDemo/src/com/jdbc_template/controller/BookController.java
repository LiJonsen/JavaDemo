package com.jdbc_template.controller;
import com.jdbc_template.beans.Book;
import com.jdbc_template.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName BookController
 * @Description
 * @Author Josen
 * @Create 2020/7/28 11:19
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    public void addBook(Book book){
        System.out.println("Controller ---> 执行添加Book操作...");
        bookService.validationBook(book);
    }
}
