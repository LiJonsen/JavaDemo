<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index Page</title>
    <script src="static/jquery.min-1.7.2.js"></script>
    <script>
        $(function () {
            $("#getEmpListBtn").on("click",()=>{
                sendReq("post","getEmployeeList")
            })

            $("#sendEmpJson").on("click",()=>{
                let data = {
                    name:"Curry",
                    age:30,
                }

                sendReq("post","addEmployee",data)
            })

            function sendReq(method = 'get', url, data) {
                $.ajax({
                    type: method,
                    url: url,
                    data: JSON.stringify(data),
                    headers: {
                        "Content-Type": "application/json;charset=UTF-8"
                    },
                    success: (res) => {
                        console.log(res);
                    },
                    error: err => {
                        console.log(err)
                    }
                })
            }
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>


<p>*******************************</p>
<ul>
    <li>
        <button type="button" id="getEmpListBtn">测试获取JSON列表</button>
    </li>
    <li>
<%--        <button type="button" id="sendEmpJson">测试发送JSON请求</button>--%>
    </li>
    <li>

    </li>
</ul>


</body>
</html>
