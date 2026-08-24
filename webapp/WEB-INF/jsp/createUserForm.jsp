<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create user</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Create user</h1>
<form action="${pageContext.request.contextPath}/users/create" method="post">
    <label for="input-email">Email</label>
    <input id="input-email" type="email" name="email" required>
    <br/><br/>
    <label for="input-password">Password</label>
    <input id="input-password" type="password" name="password" required>
    <br/><br/>
    <label for="input-role">Role</label>
    <select id="input-role" name="role" required>
        <option value="GUEST">GUEST</option>
        <option value="USER" selected>USER</option>
        <option value="MANAGER">MANAGER</option>
        <option value="ADMIN">ADMIN</option>
    </select>
    <br/><br/>
    <label for="input-first-name">First name</label>
    <input id="input-first-name" type="text" name="firstName">
    <br/><br/>
    <label for="input-last-name">Last name</label>
    <input id="input-last-name" type="text" name="lastName">
    <br/><br/>
    <input type="submit" value="Create">
</form>
<br/>
<a href="${pageContext.request.contextPath}/users/getAll">Back to users</a>
</body>
</html>