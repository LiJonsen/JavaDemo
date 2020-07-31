<%--
  Created by IntelliJ IDEA.
  User: Jonsen
  Date: 2020/7/30
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
    <!--
    判断是添加操作还是修改操作:
    根据回显的Employee对象的id值来判断: 如果有id就是修改 如果没有id就是添加操作
    -->
    <c:if test="${!empty employee.id }" var="flag">
        <!-- 修改操作 -->
        <form:hidden path="id"/>
        <!-- 隐藏PUT -->
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>


    lastName:<form:input path="lastName"/> <!-- path就相当于HTML中input标签中的name属性 -->
    <!-- <input type="text" name="lastName"/> -->
    <br/>
    Email: <form:input path="email"/>
    <br/>
    <!-- radiobuttons 可以根据Map数据来生成单选框 -->
    Gender:<form:radiobuttons path="gender" items="${genders }"/>
    <br/>
    deptName:<form:select path="department.id" items="${depts }" itemLabel="departmentName"
                          itemValue="id"></form:select>

    <!-- <select name="department.id">
    <option value="1">开发部</option>
    <option value="2">测试部</option>
    </select> -->
    <br/>
    <c:if test="${flag }">
        <input type="submit" value="Edit"/>
    </c:if>
    <c:if test="${!flag }">
        <input type="submit" value="ADD"/>
    </c:if>
</form:form>
</body>
</html>
