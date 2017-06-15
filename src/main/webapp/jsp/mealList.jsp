<%@ page import="ru.javawebinar.topjava.util.DateTimeUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>meals</title>
    <link rel="stylesheet" href="/topjava/css/style.css">
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h1>Еда</h1>
<a href="?action=edit">Добавить</a>
<table>
    <thead>
    <tr>
        <td>Изменить</td>
        <td>Дата</td>
        <td>Название</td>
        <td>Калории</td>
        <td>Превышение</td>
        <td>Удалить</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.dto.MealWithExceed"/>
        <tr class="${meal.exceed?'exceed':'not_exceed'}">
            <td><a name="meal${meal.id}" href="?action=edit&id=${meal.id}">Изменить</a></td>
            <td><%=DateTimeUtil.toString(meal.getDateTime())%></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.exceed}</td>
            <td><a href="?action=remove&id=${meal.id}">x</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
