<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>User</h1>
<h2>Id</h2>
<p>${user.id}</p>
<h2>Email</h2>
<p>${user.email}</p>
<h2>Role</h2>
<p>${user.role}</p>
<h2>First name</h2>
<p>${user.firstName}</p>
<h2>Last name</h2>
<p>${user.lastName}</p>
<br/>
<a href="${pageContext.request.contextPath}/users/edit/${user.id}">Edit user</a>
<br/><br/>
<a href="${pageContext.request.contextPath}/users/getAll">Back to users</a>
</body>
</html>