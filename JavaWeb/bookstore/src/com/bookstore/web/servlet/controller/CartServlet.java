package com.bookstore.web.servlet.controller;

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Cart;
import com.bookstore.pojo.CartItem;
import com.bookstore.pojo.ResponseData;
import com.bookstore.service.book.BookService;
import com.bookstore.service.book.BookServiceImpl;
import com.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CartServlet
 * @Description 购物车Servlet程序
 * @Author Josen
 * @Date 2020/6/12 14:30
 * @Version 1.0
 **/
public class addShopToCartCartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 将商品添加到购物车
     * @param req
     * @param resp
     */
    protected void addShopToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据bookId查询书籍信息添加到购物车
        int bookId = WebUtils.ParseStringToInt(req.getParameter("bookId"),-1);

        if(bookId==-1){
            WebUtils.SetCommonResultMsg(req,new ResponseData("bookId参数错误",-1,null));
            req.getRequestDispatcher("").forward(req, resp);
            return;
        }
        Book book = bookService.queryBookById(bookId);

        // 将书籍信息填入到商品项中
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice());

        // 获取Session中的Cart购物车信息
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            // 当前购物车为空
            cart = new Cart();
        }
        cart.addCartItem(cartItem);
        req.getSession().setAttribute("cart",cart);
        WebUtils.SetCommonResultMsg(req,new ResponseData<String>("添加成功",200,cartItem.getName()));
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("cart");
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }
    /**
     * 删除指定id的购物车商品
     * @param req
     * @param resp
     */
    protected void deleteCartItemById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.ParseStringToInt(req.getParameter("id"), -1);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(id!=-1 && cart!=null){
            cart.deleteCartItemById(id);
            WebUtils.SetCommonResultMsg(req,new ResponseData("删除成功",200,null));
        }else{
            WebUtils.SetCommonResultMsg(req,new ResponseData("删除失败",-1,null));
        }
        req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req, resp);

    }
}
