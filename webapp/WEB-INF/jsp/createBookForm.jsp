<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="addBook.Title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="addBook.AddNewBook"/></h1>
<form action="${pageContext.request.contextPath}/books/create" method="post">
    <label for="input-name"><fmt:message key="addBook.Name"/></label>
    <input id="input-name" type="text" name="name" required>
    <br/><br/>
    <label for="input-author"><fmt:message key="addBook.Author"/></label>
    <input id="input-author" type="text" name="author">
    <br/><br/>
    <label for="input-price"><fmt:message key="addBook.Price"/></label>
    <input id="input-price" type="number" name="price" step="0.01" min="0" required>
    <br/><br/>
    <input type="submit" value="Create">
</form>
</body>
</html>