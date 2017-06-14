<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="meal" scope="page" value="${requestScope.meal}"/>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal"/>
<head>
    <title>${meal.id == 0 ? 'Создание еды' : 'Редактирование еды'}</title>
    <link rel="stylesheet" href="/topjava/css/style.css">
</head>
<body>
<h1>${requestScope.title}</h1>
<form method="post" action="meals?action=save" accept-charset="UTF-8">
    <fieldset>
        <input type="hidden" name="id" value="${meal.id}">
        <p><label for="description">Название</label><input id="description" name="description"  type="text" value="${meal.description}"></p>
        <p><label for="calories">Калории</label><input id="calories" name="calories" type="number" value="${meal.calories}"></p>
        <p><label for="dateTime">Время</label><input id="dateTime" name="dateTime" type="datetime-local" value="${meal.dateTime}"></p>
        <input type="submit" title="ok">
    </fieldset>
</form>
</body>
</html>
