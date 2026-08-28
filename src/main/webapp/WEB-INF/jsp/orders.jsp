<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Orders</h1>
<a href="${pageContext.request.contextPath}/orders/create">Create order</a>
<c:choose>
    <c:when test="${empty orders}"><h2>Orders not found</h2></c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>User</th>
                <th>Status</th>
                <th>Cost</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orders/${order.id}">${order.user.email}</a>
                    </td>
                    <td>${order.status}</td>
                    <td>${order.cost}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orders/${order.id}">View</a>
                        <a href="${pageContext.request.contextPath}/orders/edit/${order.id}">Edit</a>
                        <form action="${pageContext.request.contextPath}/orders/delete/${order.id}" method="post" style="display: inline">
                            <input type="submit" value="Delete" onclick="return confirm('Delete this order?')">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <a href="${pageContext.request.contextPath}/orders/getAll?page=1&pageSize=${pageSize}">First</a>
            <a href="${pageContext.request.contextPath}/orders/getAll?page=${page <= 1 ? 1 : page - 1}&pageSize=${pageSize}">Previous</a>
            <span>Page ${page} of ${totalPages}</span>
            <a href="${pageContext.request.contextPath}/orders/getAll?page=${page < totalPages ? page + 1 : totalPages}&pageSize=${pageSize}">Next</a>
            <a href="${pageContext.request.contextPath}/orders/getAll?page=${totalPages}&pageSize=${pageSize}">Last</a>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>