<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/header.jsp" %>
</head>
<body>

	<%@include file="/pages/common/navigation.jsp" %>
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.value.name}</td>
					<td>${item.value.count}</td>
					<td>${item.value.price}</td>
					<td>${item.value.totalPrice}</td>
					<td><a href="CartServlet?action=deleteCartItemById&id=${item.value.id}">删除</a></td>
				</tr>
			</c:forEach>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="CartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp" %>

</body>
</html>