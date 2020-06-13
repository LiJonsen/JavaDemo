package com.bookstore.web.servlet.controller;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.service.book.BookServiceImpl;
import com.bookstore.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HomeServlet
 * @Description 处理首页发送的请求
 * @Author Josen
 * @Date 2020/6/11 14:17
 * @Version 1.0
 **/
public class HomeServlet extends BaseServlet{
    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 首页列表展示
     * @param req
     * @param resp
     */
    protected void queryList(HttpServletRequest req, HttpServletResponse resp){
        // 获取请求参数-current=当前页码、pageSize=每页展示的条数
        int current = WebUtils.ParseStringToInt(req.getParameter("current"),1);
        int pageSize = WebUtils.ParseStringToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 获取价格区间搜索
        int min = WebUtils.ParseStringToInt(req.getParameter("min"), 0);
        int max = WebUtils.ParseStringToInt(req.getParameter("max"), Integer.MAX_VALUE);

        Page<Book> bookPage = bookService.queryBooks(current, pageSize,min,max);
        System.out.println("bookPage:"+bookPage);
        bookPage.setUrl("home?action=queryList");
        req.setAttribute("res",bookPage);
        try {
            req.getRequestDispatcher("/pages/home/home.jsp").forward(req,resp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
