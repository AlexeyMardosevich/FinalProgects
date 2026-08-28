<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Create order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Create order</h1>
<form action="${pageContext.request.contextPath}/orders/create"
      method="post">
    <label for="input-user-id">User id</label>
    <input id="input-user-id" type="number" name="userId" min="1" required>
    <br/><br/>
    <label for="input-status">Status</label>
    <select id="input-status" name="status" required>
        <option value="PENDING" selected>PENDING</option>
        <option value="PAID">PAID</option>
        <option value="DELIVERED">DELIVERED</option>
        <option value="CANCELED">CANCELED</option>
    </select>
    <br/><br/>
    <label for="input-cost">Cost</label>
    <input id="input-cost"
           type="number"
           name="cost"
           step="0.01"
           min="0"
           required>
    <br/><br/>
    <input type="submit" value="Create">
</form>
</body>
</html>