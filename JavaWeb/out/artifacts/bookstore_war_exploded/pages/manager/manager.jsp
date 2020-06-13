<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%@include file="/pages/common/header.jsp" %>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
<%--<%@include file="/pages/common/navigation.jsp" %>--%>

<div id="header">
	<a href="">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
	</a>
	<span class="wel_word">网上书城</span>
	<div>
		<span>欢迎</span>
		<strong style="color: orange;">${sessionScope.username}</strong>
		<span>用户登录</span>
		<a href="pages/cart/cart.jsp">购物车</a>
		<a href="manager/books?action=queryList">图书管理</a>
	</div>

</div>
<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>