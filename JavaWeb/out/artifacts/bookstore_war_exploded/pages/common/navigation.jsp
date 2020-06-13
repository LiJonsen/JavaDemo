<%--
  Created by IntelliJ IDEA.
  User: MingZhen
  Date: 2020/6/9
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <a href="">
        <img class="logo_img" alt="" src="static/img/logo.gif" >
    </a>
    <span class="wel_word">网上书城</span>
    <c:if test="${empty sessionScope.username}">
        <div>
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </div>
    </c:if>
    <c:if test="${!empty sessionScope.username}">
        <div>
            <span>欢迎</span>
            <strong style="color: orange;">${sessionScope.username}</strong>
            <span>用户登录</span>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="manager/books?action=queryList">图书管理</a>
            <a href="userServlet?action=logout">退出登录</a>
        </div>
    </c:if>

</div>
