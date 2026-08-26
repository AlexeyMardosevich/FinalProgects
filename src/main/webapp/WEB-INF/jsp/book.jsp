<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <title>
        <fmt:message bundle="${msg}" key="book.Title"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>
    <fmt:message bundle="${msg}" key="book.Book"/>
</h1>

<h2>
    <fmt:message bundle="${msg}" key="book.Id"/>
</h2>

<p>
    ${book.id}
</p>

<h2>
    <fmt:message bundle="${msg}" key="book.Name"/>
</h2>

<p>
    ${book.name}
</p>

<h2>
    <fmt:message bundle="${msg}" key="book.Author"/>
</h2>

<p>
    ${book.author}
</p>

<h2>
    <fmt:message bundle="${msg}" key="book.Price"/>
</h2>

<p>
    ${book.price}
</p>

<hr/>

<h2>Add to cart</h2>

<form
        action="${pageContext.request.contextPath}/cart/items"
        method="post">

    <input type="hidden"
           name="bookId"
           value="${book.id}">

    <label for="quantity">
        Quantity:
    </label>

    <input id="quantity"
           type="number"
           name="quantity"
           value="1"
           min="1"
           required>

    <button type="submit">
        Add to cart
    </button>
</form>

<br/>

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

<br/>
<br/>

<a href="${pageContext.request.contextPath}/books/getAll">
    Back to books
</a>

<br/>

<a href="${pageContext.request.contextPath}/cart">
    Open cart
</a>

</body>
</html>