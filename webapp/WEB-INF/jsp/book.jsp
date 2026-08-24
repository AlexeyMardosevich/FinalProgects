<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="book.Title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="book.Book"/></h1>
<h2><fmt:message key="book.Id"/></h2>
<p>${book.id}</p>
<h2><fmt:message key="book.Name"/></h2>
<p>${book.name}</p>
<h2><fmt:message key="book.Author"/></h2>
<p>${book.author}</p>
<h2><fmt:message key="book.Price"/></h2>
<p>${book.price}</p>
<br/>
<a href="${pageContext.request.contextPath}/books/edit/${book.id}">Edit</a>
<form action="${pageContext.request.contextPath}/books/delete/${book.id}"
      method="post"
      style="display: inline">
    <input type="submit" value="Delete" onclick="return confirm('Delete this book?')">
</form>
<br/><br/>
<a href="${pageContext.request.contextPath}/books/getAll">Back to books</a>
</body>
</html>