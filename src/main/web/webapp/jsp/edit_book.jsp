<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="editBook.Title"/></title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="editBook.NewBook"/></h1>
<form action="controller?command=edit_book" method="post">
    <input type="hidden" name="id" value="${requestScope.book.id}">
    <label for="input-name"><fmt:message key="editBook.Name"/></label>
    <input id="input-name" type="text" name="name" value="${requestScope.book.name}">
    <label for="input-author"><fmt:message key="editBook.Author"/></label>
    <input id="input-author" type="text" name="author" value="${requestScope.book.author}">
    <label for="input-price"><fmt:message key="editBook.Price"/></label>
    <input id="input-price" type="number" name="price" value="${requestScope.book.price}">
    <br/>
    <input type="submit" name="Edit">
</form>
</body>
</html>
