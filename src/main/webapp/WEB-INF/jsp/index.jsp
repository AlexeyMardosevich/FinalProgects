<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<c:if test="${sessionScope.lang != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="home.Name"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Bookstore</h1>
<img src="images/bookstore.png" alt="Bookstore">
<c:if test="${sessionScope.user != null}">
    <h2><fmt:message key="home.Welcome"/>${sessionScope.user.firstName && sessionScope.user.lastName}!</h2>
</c:if>
<c:if test="${sessionScope.user == null}">
<h2><fmt:message key="home.WelcomeGuest"/></h2>
</c:if>
    <a href="controller?command=books"><fmt:message key="home.Catalog"/></a>
<c:choose>
    <c:when test="${empty cart or empty cart.items}">
        <h2>Your cart is empty</h2>
        <a href="${pageContext.request.contextPath}/books/getAll">Start shopping</a>
    </c:when>
    <c:otherwise>
        <section class="cart-preview">
            <h2>Your cart</h2>
            <p>Items:${cart.items.size()}</p>
            <table>
                <thead>
                <tr>
                    <th>Book</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.items}" var="item">
                    <tr>
                        <td>${item.bookName}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.price}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h3>Total:<fmt:formatNumber value="${cart.cost}" minFractionDigits="2" maxFractionDigits="2"/></h3>
            <a href="${pageContext.request.contextPath}/cart">Open cart</a>
        </section>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.request.contextPath}/books/getAll">View books</a>
</body>
</html>