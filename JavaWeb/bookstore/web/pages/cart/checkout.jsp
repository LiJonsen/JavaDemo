<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<%@include file="/pages/common/header.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

<%@include file="/pages/common/navigation.jsp" %>

	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>

	
	</div>

<%@include file="/pages/common/footer.jsp" %>

</body>
</html>