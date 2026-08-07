<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><Books</title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Books</h1>
<c:if test="${requestScope.books.isEmpty()}">
    <h2>No books available!</h2>
</c:if>
<c:if test="${requestScope.books.isEmpty()}">
    <table>
        <thead>
        <th>Id</th>
        <th>Name</th>
        <th>Author</th>
        <th>Price</th>
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