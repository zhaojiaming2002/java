<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/7/5
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改账号</title>
</head>
<body>
<h1>修改账号信息</h1>
<div style="color: red">${errorMessage}</div>
<form action="${pageContext.request.contextPath}/account?method=updateAccountById" method="post">
    <input type="hidden" name="id" value="${account.id}">
    用户名:<input type="text" name="name" value="${account.name}"/><br/>
    余额:<input type="text" name="balance" value="${account.balance}"/><br/>
    状态:<input type="text" name="status" value="${account.status}"/><br/>
    <input type="submit" value="提交">
</form>

</body>
</html>
