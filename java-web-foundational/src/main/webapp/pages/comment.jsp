<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/24
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评论</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/comments">
    评论:<input type="text" name="comments">
    <input type="submit" value="提交">
</form>
</body>
</html>
