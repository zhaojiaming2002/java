<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/8
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录V2</title>
</head>
<body>
<%
    String errorMessage = errorMessage = (String) request.getAttribute("errorMessage");

    if (errorMessage == null) {
        errorMessage = "";
    }

%>
<div style="color: red">
    <%=errorMessage%>
</div>
<form method="post" action="${pageContext.request.contextPath}/login">
    用户名:<input type="text" name="name" autocomplete="off"><br/>
    密码:<input type="password" name="password" autocomplete="off"><br/>
    验证码:<input type="text" name="captcha" autocomplete="off"><br/>
    是否记住密码<input type="checkbox" name="renumber"><br/>
    <img src="${pageContext.request.contextPath}/captcha" width="130px" height="48px" id="captcha"/></br>
    <input type="submit" value="登录"/><br/>
</form>
<script>

    let element = document.querySelector("#captcha");
    element.addEventListener("click", function () {
        this.setAttribute("src", "${pageContext.request.contextPath}/captcha?date=" + new Date());
    });

</script>
</script>
</body>
</html>
