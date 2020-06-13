package com.bookstore.dao.book;

import com.bookstore.dao.BaseDao;
import com.bookstore.pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BookDaoImpl
 * @Description BookDao接口实现类
 * @Author Josen
 * @Date 2020/6/10 13:28
 * @Version 1.0
 **/
public class BookDaoImpl extends BaseDao<Book> implements BookDao{
    @Override
    public int addBook(Book book) {
        String sql = "insert into books(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBook(int id) {
        String sql = "delete from books where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update books set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select * from books where id=?";
        return queryForBean(sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from books";
        return queryList(sql);
    }

    @Override
    public List<Book> queryBooksByPagination(int begin, int pageSize) {
        String sql = "select * from books limit ?,?";
        return queryList(sql,begin,pageSize);
    }

    @Override
    public List<Book> queryBooksByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select * from books where price between ? and ? limit ?,?";
        return queryList(sql,min,max,begin,pageSize);
    }

    @Override
    public Long queryBooksCount() {
        String sql = "select count(*) from books";
        return scalarQuery(sql);
    }

    @Override
    public Long queryBooksCountByPrice(int min, int max) {
        String sql = "select count(*) from books where price between ? and ?";
        return scalarQuery(sql,min,max);
    }
}
