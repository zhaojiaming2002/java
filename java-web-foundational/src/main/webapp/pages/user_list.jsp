<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/13
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示用户数据</title>
</head>
<body>

<table border="1" width="800px" cellpadding="0">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>创建时间</th>
        <th>更新时间</th>
    </tr>

    <c:forEach var="userVO" items="${userVOList}" varStatus="status">
        <tr>
            <th>${status.count}</th>
            <th>${userVO.name}</th>
            <th>${userVO.password}</th>
            <th>${userVO.createDate}</th>
            <th>${userVO.upDateDate}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
