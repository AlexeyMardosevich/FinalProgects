<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<head>
    <title>Edit order item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Edit order item</h1>
<form action="${pageContext.request.contextPath}/orders/items/${item.id}/edit" method="post">
    <input type="hidden" name="id" value="${item.id}">
    <input type="hidden" name="orderId" value="${item.orderId}">
    <label for="bookId">Book id</label>
    <input id="bookId" type="number" name="bookId" value="${item.bookId}" min="1" required>
    <br/><br/>
    <label for="quantity">Quantity</label>
    <input id="quantity" type="number" name="quantity" value="${item.quantity}" min="1" required>
    <br/><br/>
    <input type="submit" value="Save">
</form>
<br/>
<a href="${pageContext.request.contextPath}/orders/${item.orderId}/items">Back to items</a>
</body>
</html>