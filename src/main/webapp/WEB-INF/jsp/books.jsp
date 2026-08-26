<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <title>
        <fmt:message bundle="${msg}" key="books.Title"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>
    <fmt:message bundle="${msg}" key="books.Books"/>
</h1>

<p>
    <a href="${pageContext.request.contextPath}/books/create">
        Add book
    </a>
</p>

<c:choose>

    <c:when test="${empty books}">
        <h2>
            <fmt:message bundle="${msg}" key="books.BooksMessage"/>
        </h2>
    </c:when>

    <c:otherwise>

        <table>
            <thead>
            <tr>
                <th>
                    <fmt:message bundle="${msg}" key="books.Id"/>
                </th>

                <th>
                    <fmt:message bundle="${msg}" key="books.Name"/>
                </th>

                <th>
                    <fmt:message bundle="${msg}" key="books.Author"/>
                </th>

                <th>
                    <fmt:message bundle="${msg}" key="books.Price"/>
                </th>

                <th>
                    Actions
                </th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${books}" var="book">

                <tr>
                    <td>
                            ${book.id}
                    </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/books/${book.id}">
                                ${book.name}
                        </a>
                    </td>

                    <td>
                            ${book.author}
                    </td>

                    <td>
                            ${book.price}
                    </td>

                    <td>

                        <a href="${pageContext.request.contextPath}/books/edit/${book.id}">
                            Edit
                        </a>

                        <form
                                action="${pageContext.request.contextPath}/books/delete/${book.id}"
                                method="post"
                                style="display: inline">

                            <button type="submit"
                                    onclick="return confirm('Delete this book?')">
                                Delete
                            </button>
                        </form>

                        <form
                                action="${pageContext.request.contextPath}/cart/items"
                                method="post"
                                style="display: inline">

                            <input type="hidden"
                                   name="bookId"
                                   value="${book.id}">

                            <input type="number"
                                   name="quantity"
                                   value="1"
                                   min="1"
                                   required>

                            <button type="submit">
                                Add to cart
                            </button>
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