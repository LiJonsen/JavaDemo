package com.bookstore.web.servlet.controller;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.service.book.BookServiceImpl;
import com.bookstore.utils.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName BookManage
 * @Description 处理图书模块增删改查请求
 * @Author Josen
 * @Date 2020/6/10 14:23
 * @Version 1.0
 **/
public class BookManager extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 处理查询图书列表请求
     * @param req
     * @param resp
     */
    protected void queryList(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求参数-current=当前页码、pageSize=每页展示的条数
        int current = WebUtils.ParseStringToInt(req.getParameter("current"),1);
        int pageSize = WebUtils.ParseStringToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> bookPage = bookService.queryBooks(current, pageSize);
        bookPage.setUrl("manager/books?action=queryList");
        req.setAttribute("res",bookPage);
        try {
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理添加图书信息请求
     * @param req
     * @param resp
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp){
        try {
            Book book = WebUtils.TransferParamsToBean(req.getParameterMap(), new Book());
            bookService.addBook(book);
            // 重定向返回图书列表页
            // 地址： / 工程名 /manager/bookServlet?action=list
            resp.sendRedirect(req.getContextPath()+"/manager/books?action=queryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理修改图书信息请求
     * @param req
     * @param resp
     */
    protected void edit(HttpServletRequest req, HttpServletResponse resp){
        try {
            Book book = WebUtils.TransferParamsToBean(req.getParameterMap(), new Book());
            bookService.updateBook(book);
            // 重定向返回图书列表页
            // 地址： / 工程名 /manager/bookServlet?action=list
            resp.sendRedirect(req.getContextPath()+"/manager/books?action=queryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理获取指定id的图书信息请求
     * @param req
     * @param resp
     */
    protected void queryBookById(HttpServletRequest req, HttpServletResponse resp){
        String str_id = req.getParameter("id");
        try{
            int id = Integer.parseInt(str_id);
            Book book = bookService.queryBookById(id);
            req.setAttribute("book",book);
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp){
        String str_id = req.getParameter("id");
        try{
            int id = Integer.parseInt(str_id);
            bookService.deleteBook(id);
            resp.sendRedirect(req.getContextPath()+"/manager/books?action=queryList");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
