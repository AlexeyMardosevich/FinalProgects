<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<html>
<head>
    <title>Edit user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Edit user</h1>
<form action="${pageContext.request.contextPath}/users/edit/${user.id}" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label for="input-email">Email</label>
    <input id="input-email" type="email" name="email" value="${user.email}" required>
    <br/><br/>
    <label for="input-role">Role</label>
    <select id="input-role" name="role" required>
        <option value="GUEST" <c:if test="${user.role == 'GUEST'}">selected</c:if>>GUEST</option>
        <option value="USER" <c:if test="${user.role == 'USER'}">selected</c:if>>USER</option>
        <option value="MANAGER" <c:if test="${user.role == 'MANAGER'}">selected</c:if>>MANAGER</option>
        <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
    </select>
    <br/><br/>
    <label for="input-first-name">First name</label>
    <input id="input-first-name" type="text" name="firstName" value="${user.firstName}">
    <br/><br/>
    <label for="input-last-name">Last name</label>
    <input id="input-last-name" type="text" name="lastName" value="${user.lastName}">
    <br/><br/>
    <input type="submit" value="Save changes">
</form>
<br/>
<a href="${pageContext.request.contextPath}/users/${user.id}">Back to user</a>
</body>
</html>