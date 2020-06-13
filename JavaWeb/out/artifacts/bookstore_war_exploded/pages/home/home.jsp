<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/header.jsp" %>
</head>
<body>
<%@include file="/pages/common/navigation.jsp" %>
<%
    Page res = (Page)request.getAttribute("res");
    int min = res.getMin();
    int max = res.getMax();
    if(min>0){
        request.setAttribute("q_min","&min="+min);
        request.setAttribute("min_val",min);
    }
    if(max!=Integer.MAX_VALUE){
        request.setAttribute("q_max","&max="+max);
        request.setAttribute("max_val",max);
    }
%>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="home" method="post">
                <input type="hidden" name="action" value="queryList">
                价格：<input id="min" type="text" name="min" value="${requestScope.min_val}"> 元 -
                <input id="max" type="text" name="max" value="${requestScope.max_val}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有${empty sessionScope.cart.totalCount?0:sessionScope.cart.totalCount}件商品</span>
            <c:if test="${not empty sessionScope.commonRes.data && not empty sessionScope.cart.totalCount}">
                <div>
                    您刚刚将<span style="color: red">${sessionScope.commonRes.data}</span>加入到了购物车中
                </div>
            </c:if>
        </div>
        <c:forEach items="${requestScope.res.list}" var="item">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${item.img_path}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${item.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${item.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${item.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${item.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${item.stock}</span>
                    </div>
                    <div class="book_add">
                        <button id="AddToCart" bookId="${item.id}">加入购物车</button>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>

    <div id="page_nav">

        <%@include file="/pages/common/Pagination.jsp" %>

    </div>
    <%@include file="/pages/common/footer.jsp" %>

    <script>
        $(function () {
            $("button#AddToCart").on("click",function () {
                let bookId = $(this).attr("bookId");
                window.location.href = "CartServlet?action=addShopToCart&bookId="+bookId;
            });
        })
    </script>
</body>
</html>