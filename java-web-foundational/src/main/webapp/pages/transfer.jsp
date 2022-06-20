<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/18
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>转账</title>
</head>
<body>
<div style="color: red" >${transferMessage}</div>
<form action=${pageContext.request.contextPath}/transfer method="post">

    转账用户名:<input type="text" name="fromAccountName" autocomplete="off"/> </br>
    收款用户名:<input type="text" name="toAccountName" autocomplete="off"> </br>
    转账金额:<input type="text" name="amount" autocomplete="off"></br>
    <input type="submit" value="提交"></br>

</form>
</body>
</html>
