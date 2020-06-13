package com.bookstore.service.book;

import com.bookstore.dao.book.BookDao;
import com.bookstore.dao.book.BookDaoImpl;
import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description BookService接口实现类
 * @Author Josen
 * @Date 2020/6/10 13:48
 * @Version 1.0
 **/
public class BookServiceImpl implements BookService{
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public int addBook(Book book){
        return bookDao.addBook(book);
    }

    @Override
    public int deleteBook(int id){
        return bookDao.deleteBook(id);
    }

    @Override
    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id){
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks(){
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> queryBooks(int current, int pageSize) {

        // 获取总记录数
            int count = bookDao.queryBooksCount().intValue();
            // 获取分页结果集
            List<Book> books = bookDao.queryBooksByPagination( (current-1)*pageSize, pageSize);
            // 总页码数=(总记录数+分页条数-1)/分页条数
            int pageTotal = (count + pageSize-1)/pageSize;
            // 实例化Page分页JavaBean
            return new Page(books, current, pageTotal, count, pageSize);
    }

    @Override
    public Page<Book> queryBooks(int current, int pageSize, int min, int max) {
            // 获取总记录数
            int count = bookDao.queryBooksCountByPrice(min,max).intValue();
            // 获取分页结果集
            List<Book> books = bookDao.queryBooksByPrice((current-1)*pageSize, pageSize,min,max);
            // 总页码数=(总记录数+分页条数-1)/分页条数
            int pageTotal = (count + pageSize-1)/pageSize;
            // 实例化Page分页JavaBean
            return new Page(books, current, pageTotal, count, pageSize,min,max);
    }

}
