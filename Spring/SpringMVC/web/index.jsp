<%--
  Created by IntelliJ IDEA.
  User: Jonsen
  Date: 2020/7/28
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Heelo spmvc</title>
    <script src="static/jquery.min-1.7.2.js"></script>
    <script>
      $(function () {
        $("#JsonPostBtn").on("click",()=>{
            sendReq('post','testPostJson');
        });

        $("#sendJsonBtn").on("click",()=>{
          sendReq('post','getClientJson',{message:'hello',data:{name:"josen",age:22}})
        })

        function sendReq(method='get',url,data) {
          $.ajax({
            type:method,
            url:url,
            data:data,
            success:(res)=>{
              console.log(res);
            },
            error:err=>{
              console.log(err)
            }
          })
        }
      })
    </script>
  </head>

  <body>
    <%--  测试上传文件  --%>
    <form action="uploadImg" method="post" enctype="multipart/form-data">
      <input type="file" name="file" />
      <input type="text" name="desc"/>
      <input type="submit" value="Upload Image"/>
    </form>
    <br/>
  <ul>
    <%--  测试自定义过滤器  --%>
      <li>
        <a href="testInterceptor">测试自定义过滤器</a>
      </li>
    <%--   测试Json请求   --%>
    <li>
      <a href="javascript:;" id="sendJsonBtn">Send Json To Server</a>
      <br/>
    </li>
      <li>
      <a href="javascript:;" id="JsonBtn">Test Get Json</a>
      <br/>
      <a href="javascript:;" id="JsonPostBtn">Test Post Json</a>
    </li>
    <li>
      <a href="employeeList">Link To Employee List</a>
    </li>
    <li>
      <a href="testMvcView">Test Mvc View</a>
    </li>
    <li>
      <a href="testRedirect">Test Redirect</a>
    </li>
  </ul>
    <a href="hello">跳转hello.jsp</a>
    <a href="testREST.html">跳转testREST</a>

    <a href="testParams?username=James&age=22">testParams</a>
    <a href="testHeader">testHeader</a>
    <a href="testPojo">testPojo</a>

  <form action="/sp_mvc/testPojo" method="post">
    用户名称: <input type="text" name="name"/>
    <br/>
    用户密码: <input type="text" name="age"/>
    <br/>
    用户邮箱: <input type="text" name="email"/>
    <br/>
    用户性别: 男 <input type="radio" name="gender" value="1"/>
    女<input type="radio" name="gender" value="0"/>
    <br/>
    <!-- 支持级联的方式 -->
    用户省份: <input type="text" name="address.location" />
    <br/>

    <input type="submit" value="注册"/>
  </form>
  </body>
</html>
