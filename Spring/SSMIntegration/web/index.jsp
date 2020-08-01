<%--
  Created by IntelliJ IDEA.
  User: Jonsen
  Date: 2020/7/31
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <script src="static/jquery.min-1.7.2.js"></script>
    <script>
      $(function(){
        $("#sendJsonBtn").on("click",()=>{
          let data = {message:'hello',data:{name:"josen",age:22}};
          sendReq('post','getClientJson',data)
        })

        function sendReq(method='get',url,data) {
          $.ajax({
            type:method,
            url:url,
            data:JSON.stringify(data),
            headers:{
              "Content-Type":"application/json;charset=UTF-8"
            },
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
  <ul>
    <li>
      <a href="addUser?name=josen&skill=coding">Test Add User</a>
    </li>
    <li>
      <img src="static/girl_2.jpg" style="width: 100px;" alt=""/>
    </li>
    <li>
      <button id="sendJsonBtn">Send Json To Spring</button>
    </li>
  </ul>
  </body>
</html>
