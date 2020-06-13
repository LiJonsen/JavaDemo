<%@ page import="com.bookstore.pojo.Page" %><%--
  Created by IntelliJ IDEA.
  User: MingZhen
  Date: 2020/6/11
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${requestScope.res.current>1}">
    <a href="${requestScope.res.url}&current=${requestScope.res.current-1}${requestScope.q_min}${requestScope.q_max}">
        上一页
    </a>
</c:if>
<c:forEach begin="1" end="${requestScope.res.pageTotal}" var="num">
    <c:if test="${requestScope.res.current==num}">
        【${num}】
    </c:if>
    <a href="${requestScope.res.url}&current=${num}${requestScope.q_min}${requestScope.q_max}">
            ${num!=requestScope.res.current?num:""}
    </a>
</c:forEach>
<c:if test="${requestScope.res.current<requestScope.res.pageTotal}">
    <a href="${requestScope.res.url}&current=${requestScope.res.current+1}${requestScope.q_min}${requestScope.q_max}">下一页</a>
</c:if>

共${requestScope.res.pageTotal}页，${requestScope.res.count}条记录
<%--到第<input value="4" name="pn" id="pn_input"/>页--%>
<%--<input type="button" value="确定">--%>
</div>
