<%--
  Created by IntelliJ IDEA.
  User: Jonsen
  Date: 2020/7/30
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h1 align="center">员工信息列表</h1>
<table border="1px" align="center" width="70%" cellspacing="0px">
    <tr>
        <th>Id</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Gender</th>
        <th>DeptName</th>
        <th>Operation</th>
    </tr>

    <!-- 通过迭代模型数据，生成表格 -->
    <c:forEach items="${emps}" var="emp">
        <tr align="center">
            <td>${emp.id }</td>
            <td>${emp.lastName }</td>
            <td>${emp.email }</td>
            <td>${emp.gender==0?"女":"男"}</td>
            <td>${emp.department.departmentName }</td>
            <td>
                <a href="emp/${emp.id}">Edit </a>
                &nbsp;&nbsp;
                <!--
                    解决思路:
                        给删除的超链接绑定事件， 当触发了点击事件，可以在事件处理函数中获取到要发送的请求URL,
                        再将获取到的请求URL设置到某个表单的action属性上，再将表单提交。
                        最终将<a>的默认行为取消掉。

                 -->
                <a  class="del" href="emp/${emp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
