<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Your cart</h1>
<c:choose>
    <c:when test="${empty cart.items}">
        <p>Your cart is empty.</p>
        <a href="${pageContext.request.contextPath}/books/getAll">Back to books</a>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Book</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cart.items}" var="item">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/books/${item.bookId}">${item.bookName}</a>
                    </td>
                    <td>
                        <fmt:formatNumber value="${item.price}" minFractionDigits="2" maxFractionDigits="2"/>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart/items/${item.bookId}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1" required>
                            <button type="submit">Update</button>
                        </form>
                    </td>
                    <td><fmt:formatNumber value="${item.price * item.quantity}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart/items/${item.bookId}/remove">

                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br/>
        <h3>Total:<fmt:formatNumber value="${cart.cost}" minFractionDigits="2" maxFractionDigits="2"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/cart/checkout">
            <button type="submit">Checkout</button>
        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/books/getAll">Continue shopping</a>
    </c:otherwise>
</c:choose>
</body>
</html>