package com.bookstore.web.servlet.controller;

import com.bookstore.pojo.Cart;
import com.bookstore.pojo.User;
import com.bookstore.service.order.OrderService;
import com.bookstore.service.order.OrderServiceImpl;
import com.bookstore.service.user.UserService;
import com.bookstore.service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName OrderServlet
 * @Description 处理订单相关请求
 * @Author Josen
 * @Date 2020/6/13 10:05
 * @Version 1.0
 **/
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    private UserService userService = new UserServiceImpl();
    /**
     * 创建订单
     * @param req
     * @param resp
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String)req.getSession().getAttribute("username");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(username==null || cart == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        User user = userService.queryUsername(username);
        if(user==null){
            System.out.println("username error");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        String orderId = orderService.createOrder(cart, user.getId());

        if(orderId==null){
            System.out.println("新增订单失败");
            return;
        }

        // 新增订单成功
        req.getSession().setAttribute("orderId",orderId);
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

    }
}
