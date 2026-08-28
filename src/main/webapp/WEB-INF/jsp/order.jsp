<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Order</h1>
<h2>Id</h2>
<p>${order.id}</p>
<h2>User</h2>
<p>${order.user.email}</p>
<h2>Status</h2>
<p>${order.status}</p>
<h2>Cost</h2>
<p>${order.cost}</p>
<h2>Items</h2>
<c:choose>
    <c:when test="${empty order.items}">
        <p>Order has no items.</p>
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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.items}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.book.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}</td>
                    <td>
                            ${item.price * item.quantity}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.request.contextPath}/orders/getAll">Back to orders</a>
</body>
</html>