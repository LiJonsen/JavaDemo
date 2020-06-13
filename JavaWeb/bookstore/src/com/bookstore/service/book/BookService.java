package com.bookstore.service.book;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName bookService
 * @Description 为Web层提供操作BookDAO的方法
 * @Author Josen
 * @Date 2020/6/10 13:47
 * @Version 1.0
 **/
public interface BookService {
    /**
     * 添加书籍
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 删除指定id的书籍
     * @param id
     * @return
     */
    int deleteBook(int id);

    /**
     * 更新id=book.getId()的书籍信息
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 根据id查询指定书籍信息
     * @param id
     * @return
     */
    Book queryBookById(int id);

    /**
     * 查询所有书籍列表
     * @return
     */
    List<Book> queryBooks();

    /**
     * 分页查询书籍列表
     * @param current
     * @param pageSize
     * @return
     */
    Page<Book> queryBooks(int current, int pageSize);

    /**
     * 根据价格区间+分页查询书籍列表
     * @param current
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    Page<Book> queryBooks(int current, int pageSize,int min,int max);
}
