<%--
  Created by IntelliJ IDEA.
  User: MingZhen
  Date: 2020/6/8
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <base href="http://localhost:8080/fileLoaderTest/">
  </head>
  <body>
  <h1>上传文件到Servlet服务器</h1>
  <form action="upload" method="post" enctype="multipart/form-data">
    <p>
      <strong>文件名：</strong>
      <input type="text" name="name">
    </p>
    <p>
      <strong>上传文件：</strong>
      <input type="file" name="fileContent">
    </p>
    <input type="submit" value="提交">
  </form>


  <h1>下载test.pdf文件测试</h1>
  <form action="download" method="get">
    <input type="submit" value="下载">
  </form>
  </body>
</html>
