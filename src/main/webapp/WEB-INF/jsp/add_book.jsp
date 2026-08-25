<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<head>
    <title><fmt:message key="addBook.Title"/></title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="addBook.AddNewBook"/></h1>
<form action="controller?command=add_book" method="post">
    <label for="input-name"><fmt:message key="addBook.Name"/></label>
    <input id="input-name" type="text" name="name">
    <label for="input-author"><fmt:message key="addBook.Author"/></label>
    <input id="input-author" type="text" name="author">
    <label for="input-price"><fmt:message key="addBook.Price"/></label>
    <input id="input-price" type="number" name="price">
    <br/>
    <input type="submit" name="Create">
</form>
</body>
</html>
