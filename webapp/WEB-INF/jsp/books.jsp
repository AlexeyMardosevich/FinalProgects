<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="books.Title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="books.Books"/></h1>
<a href="${pageContext.request.contextPath}/books/create">Add book</a>
<c:choose><c:when test="${empty books}">
        <h2><fmt:message key="books.BooksMessage"/></h2>
    </c:when>
    <c:otherwise>
        <div>
            <a href="${pageContext.request.contextPath}/books/getAll?page=1">First</a>
            <a href="${pageContext.request.contextPath}/books/getAll?page=${page <= 1 ? 1 : page - 1}">Previous</a>
            <span>Page ${page} of ${totalPages}</span>
            <a href="${pageContext.request.contextPath}/books/getAll?page=${page < totalPages ? page + 1 : totalPages}">Next</a>
            <a href="${pageContext.request.contextPath}/books/getAll?page=${totalPages}">Last</a>
        </div>
        <table>
            <thead>
            <tr>
                <th><fmt:message key="books.Id"/></th>
                <th><fmt:message key="books.Name"/></th>
                <th><fmt:message key="books.Author"/></th>
                <th><fmt:message key="books.Price"/></th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/books/${book.id}">${book.name}</a>
                    </td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/books/edit/${book.id}">Edit</a>
                        <form action="${pageContext.request.contextPath}/books/delete/${book.id}" method="post" style="display: inline">
                            <input type="submit" value="Delete" onclick="return confirm('Delete this book?')">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>