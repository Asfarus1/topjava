<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: asfarus
  Date: 12.12.2016
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<link type="text/css" rel="stylesheet" href="stl.css">
<a href="?action=add">Добавить</a>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <thead>
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="meal" items="${meals}">
            <tr class="${meal.exceed?"exceeded":"no_exceeded"}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                <%--<fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/>--%>
                <%--<td><fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
                <td><%=TimeUtil.toString(meal.getDateTime())%></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="?action=edit&id=${meal.id}">Обновить</a></td>
                <td><a href="?action=delete?id=${meal.id}">Удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
</table>
</body>
</html>
