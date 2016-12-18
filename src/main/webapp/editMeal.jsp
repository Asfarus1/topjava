<%--
  Created by IntelliJ IDEA.
  User: asfarus
  Date: 17.12.2016
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<
<html>
<head>
    <%--<title>"${meal.id = 0?"Создание еды":"Редактирование еды"}"</title>--%>
</head>
<body>
<form method="post" action="meals/?action=update">
    <%--<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal"/>--%>
    <input name="id" type="hidden" value="${meal.id}">
    <input name="description" type="text" size="25" maxlength="50" value="${meal.description}"/><br>
    <input name="calories" type="number" value="${meal.calories}"/><br>
    <input name="dateTime" type="datetime" value="${meal.dateTime}"/><br>
    <input type="submit" name="Ok" value="Ok">
</form>
</body>
</html>
