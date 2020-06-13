package com.bookstore.dao.book;

import com.bookstore.pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BookDao
 * @Description 定义操作books数据表的方法封装
 * @Author Josen
 * @Date 2020/6/5 11:39
 * @Version 1.0
 **/
public interface BookDao {
    /**
     * 想books数据表添加一条记录
     * @param book
     * @return 返回数值>0，添加成功
     */
    int addBook(Book book);

    /**
     * 根据id形参，删除一条books数据表指定记录
     * @param id
     * @return 返回数值>0，删除成功
     */
    int deleteBook(int id);

    /**
     * 根据book.getId，更新一条books数据表指定记录
     * @param book
     * @return 返回数值>0，更新成功
     */
    int updateBook(Book book);

    /**
     * 根据id查询一条books数据表记录
     * @param id
     * @return 返回Book实例
     */
    Book queryBookById(int id);

    /**
     * 查询books数据表所有记录
     * @return 返回Book集合
     */
    List<Book> queryBooks();

    /**
     * 按分页查询书籍信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryBooksByPagination(int begin,int pageSize);

    /**
     * 根据价格区间+分页查询书籍信息
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     * @throws SQLException
     */
    List<Book> queryBooksByPrice(int begin,int pageSize,int min,int max);

    /**
     * 查询books总记录数
     * @return
     */
    Long queryBooksCount();

    /**
     * 按price查询总记录数
     * @param min
     * @param max
     * @return
     * @throws SQLException
     */
    Long queryBooksCountByPrice(int min,int max);


}
