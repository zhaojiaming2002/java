<%@ page import="net.microsoft.java.web.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/8
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    String name = user.getName();
%>
欢迎<%=name%>登录到淘宝网

</body>
</html>
