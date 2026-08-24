<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Users</h1>
<a href="${pageContext.request.contextPath}/users/create">Create user</a>
<c:choose>
    <c:when test="${empty users}">
        <h2>Users not found</h2>
    </c:when>
    <c:otherwise>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Role</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/users/${user.id}">${user.email}</a>
                    </td>
                    <td>${user.role}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/users/edit/${user.id}">Edit</a>
                        <form action="${pageContext.request.contextPath}/users/delete/${user.id}" method="post" style="display: inline">
                            <input type="submit" value="Delete" onclick="return confirm('Delete user ${user.email}?')">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>