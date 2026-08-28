<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Order items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Order items</h1>
<a href="${pageContext.request.contextPath}/orders/${orderId}/items/create">Add item</a>
<br/><br/>
<c:choose>
    <c:when test="${empty items}">
        <h2>No items found</h2>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.bookName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}</td>
                    <td>${item.total}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orders/items/${item.id}/edit">Edit</a>
                        <form action="${pageContext.request.contextPath}/orders/items/delete/${item.id}" method="post" style="display: inline">
                            <input type="submit" value="Delete" onclick="return confirm('Delete item?')">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.request.contextPath}/orders/${orderId}"></a>
</body>
</html>