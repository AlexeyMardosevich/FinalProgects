<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<c:if test="${not empty loginError}">
    <p style="color: red">${loginError}</p>
</c:if>
<c:if test="${not empty logoutMessage}">
    <p style="color: green">${logoutMessage}</p>
</c:if>
<form
        action="${pageContext.request.contextPath}/login"
        method="post">
    <label for="email">
        Email:
    </label>
    <input id="email" type="email" name="email" required>
    <br/>
    <br/>
    <label for="password">Password:</label>
    <input id="password" type="password" name="password" required>
    <br/>
    <br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Login</button>
</form>
</body>
</html>