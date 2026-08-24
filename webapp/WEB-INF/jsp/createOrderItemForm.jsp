<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create order item</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>Create order item</h1>

<form action="${pageContext.request.contextPath}/orders/${orderId}/items/create"
      method="post">

    <input type="hidden"
           name="orderId"
           value="${orderId}">

    <label for="bookId">Book id</label>
    <input id="bookId"
           type="number"
           name="bookId"
           min="1"
           required>

    <br/><br/>

    <label for="quantity">Quantity</label>
    <input id="quantity"
           type="number"
           name="quantity"
           value="1"
           min="1"
           required>

    <br/><br/>

    <input type="submit" value="Create">
</form>

<br/>

<a href="${pageContext.request.contextPath}/orders/${orderId}/items">
    Back to items
</a>

</body>
</html>