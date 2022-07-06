<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/28
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部用户</title>
</head>
<body>
<div style="color: red">${errorMessage}</div>
<table width="800px">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>余额</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>删除</th>
    </tr>

    <c:forEach items="${accountList}" var="account" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${account.name}</td>
            <td>${account.balance}</td>
            <td><c:if test="${account.status==1}">
                启用
            </c:if>
                <c:if test="${account.status==0}">
                    禁用
                </c:if>
            </td>
            <td>${account.createDate}</td>
            <td>${account.updateDate}</td>
            <td><a href="#" onclick="deleteAccountById('${account.id}','${account.name}')">删除</a></td>
            <td><a href="#" onclick="updateAccountById('${account.id}','${account.name}')">修改</a></td>
        </tr>
    </c:forEach>


</table>
<a href="${pageContext.request.contextPath}/pages/account/account_add.jsp" target="_blank">用户添加</a>
<script>
    function deleteAccountById(id, name) {
        console.log("你删除用户的ID是" + id);
        let flag = confirm("你确认删除" + name + "吗?");
        if (flag) {
            window.location.href = "${pageContext.request.contextPath}/account?method=deleteAccountById&id=" + id;
        }
    }

    function updateAccountById(id, name) {
        let flag = confirm("你要修改" + name + "吗?");
        if (flag) {
            window.location.href = "${pageContext.request.contextPath}/account?method=updateAccountPage&id=" + id;
        }

    }
</script>
</body>
</html>
