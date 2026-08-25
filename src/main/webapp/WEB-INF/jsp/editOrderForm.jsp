<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Edit order</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>Edit order</h1>

<form action="${pageContext.request.contextPath}/orders/edit/${order.id}"
      method="post">

    <input type="hidden"
           name="id"
           value="${order.id}">

    <p>
        User:
        ${order.user.email}
    </p>

    <label for="input-status">Status</label>

    <select id="input-status" name="status" required>
        <option value="PENDING"
                <c:if test="${order.status == 'PENDING'}">
                    selected
                </c:if>>
            PENDING
        </option>

        <option value="PAID"
                <c:if test="${order.status == 'PAID'}">
                    selected
                </c:if>>
            PAID
        </option>

        <option value="DELIVERED"
                <c:if test="${order.status == 'DELIVERED'}">
                    selected
                </c:if>>
            DELIVERED
        </option>

        <option value="CANCELED"
                <c:if test="${order.status == 'CANCELED'}">
                    selected
                </c:if>>
            CANCELED
        </option>
    </select>

    <br/><br/>

    <label for="input-cost">Cost</label>

    <input id="input-cost"
           type="number"
           name="cost"
           value="${order.cost}"
           step="0.01"
           min="0"
           required>

    <br/><br/>

    <input type="submit" value="Save">
</form>

<br/>

<a href="${pageContext.request.contextPath}/orders/${order.id}">
    Back to order
</a>

</body>
</html>