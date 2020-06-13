<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/header.jsp" %>
</head>
<body>

<%@include file="/pages/common/navigation.jsp" %>

	<div id="main">
		<table style="margin-top: 10px">
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.res.list}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/books?action=queryBookById&id=${book.id}">修改</a></td>
					<td><a href="manager/books?action=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">

			<%@include file="/pages/common/Pagination.jsp" %>

		</div>
	</div>


<%@include file="/pages/common/footer.jsp" %>

</body>
</html>