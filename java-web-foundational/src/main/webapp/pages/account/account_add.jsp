<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/30
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户添加</title>
</head>
<body>
<div style="color: red">${errorMessage}</div>
<form action="${pageContext.request.contextPath}/account?method=addAccount" method="post">
    用户名:<input type="text" name="name" autocomplete="off"/><br/>
    账户余额:<input type="text" name="balance" autocomplete="off"/><br/>
    <input type="submit" value="提交"><br/>
</form>
</body>
</html>
