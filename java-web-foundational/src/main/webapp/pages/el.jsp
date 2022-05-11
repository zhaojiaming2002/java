<%@ page import="java.util.Map" %>
<%@ page import="net.microsoft.java.web.entity.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: 75863
  Date: 2022/6/11
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el表达式的使用</title>
</head>
<body>
<%
    Map<String, String> map = new HashMap<>();
    map.put("name", "el表达式");
    request.setAttribute("map", map);


    User user = new User();
    user.setName("tony");
    user.setPassword("1111111");

    request.setAttribute("user", user);

%>
EL表达式获取复杂数据类型的数据集合${map.get("name")}<br/>
EL表达式获取复杂数据类型的数据类${user.name}<br/>
</body>
</html>
