<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title><fmt:message key="editBook.Title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="editBook.NewBook"/></h1>
<form action="${pageContext.request.contextPath}/books/edit/${book.id}" method="post">
    <input type="hidden" name="id" value="${book.id}">
    <label for="input-name"><fmt:message key="editBook.Name"/></label>
    <input id="input-name" type="text" name="name" value="${book.name}" required>
    <br/><br/>
    <label for="input-author"><fmt:message key="editBook.Author"/></label>
    <input id="input-author" type="text" name="author" value="${book.author}">
    <br/><br/>
    <label for="input-price"><fmt:message key="editBook.Price"/></label>
    <input id="input-price" type="number" name="price" value="${book.price}" step="0.01" min="0" required>
    <br/><br/>
    <input type="submit" value="Save">
</form>
<br/>
<a href="${pageContext.request.contextPath}/books/${book.id}">Back to book</a>
</body>
</html>