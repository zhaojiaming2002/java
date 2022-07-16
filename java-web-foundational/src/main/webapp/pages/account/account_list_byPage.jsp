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
    <title>查询所有用户(分页显示)</title>
</head>
<body>
<h1>查询所有用户(分页显示)</h1>
<form action="${pageContext.request.contextPath}/account?method=findAccountByName" method="post">
    用户名查询:<input type="text" name="userName" autocomplete="off"/>
    <input type="submit" value="查询"/>
</form>
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

    <c:forEach items="${pageBean.dataList}" var="account" varStatus="status">
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
<div>总条数${pageBean.totalCount},每页条数${pageBean.pageSize}</div>

<c:if test="${pageBean.pageNo!=1}">
    <a href="account?method=findAccountByPage&pageNo=${pageBean.pageNo-1}&pageSize=2">上一页</a>
</c:if>
<c:forEach begin="1" end="${pageBean.totalCountPage}" var="status">
    <c:if test="${pageBean.pageNo==status}">
        <a style="color: red" href="account?method=findAccountByPage&pageNo=${status}&pageSize=2">${status}</a>
    </c:if>
    <c:if test="${pageBean.pageNo!=status}">
        <a href="account?method=findAccountByPage&pageNo=${status}&pageSize=2">${status}</a>
    </c:if>

</c:forEach>

<c:if test="${pageBean.pageNo!=pageBean.totalCountPage}">
    <a href="account?method=findAccountByPage&pageNo=${pageBean.pageNo+1}&pageSize=2">下一页</a><br/>
</c:if>
<br/>
<a href="${pageContext.request.contextPath}/pages/account/account_add.jsp" target="_blank">用户添加</a><br/>
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
