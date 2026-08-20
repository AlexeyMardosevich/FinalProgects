<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="books.Title"/></title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="books.Books"/></h1>
<c:if test="${requestScope.books.isEmpty()}">
    <h2><fmt:message key="books.BooksMessage"/></h2>
</c:if>
<c:if test="${requestScope.books.isEmpty()}">
    <div>
    <a href="controller?command=books&page=1"><fmt:message key="books.First"/></a>
    <a href="controller?command=books&page=${page <= 1 ? 1 : page - 1}"><fmt:message key="books.Prev"/></a>${pageContext.page}
    <a href="controller?command=books&page=${totalPages > page ? totalPages : page + 1}"><fmt:message key="books.Next"/></a>
    <a href="controller?command=books&page=${pageContext.totalPages}"><fmt:message key="books.Last"/></a>
    </div>
    <table>
        <thead>
        <th><fmt:message key="books.Id"/></th>
        <th><fmt:message key="books.Name"/></th>
        <th><fmt:message key="books.Author"/></th>
        <th><fmt:message key="books.Price"/></th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td><a href="controller?command=book&id=${book.id}">${book.name}</a></td>
                <td>${book.author}</td>
                <td>${book.getPrice()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>