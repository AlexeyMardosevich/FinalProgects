<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="login.Login"/></title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="login.Login"/></h1>
<form method="post" action="controller">
    <input name="command" type="hidden" value="login"/>
    <label for="email-input"><fmt:message key="login.Email"/> </label>
    <input id="email-input" name="email" type="email">
    <br/>
    <label for="password-input"><fmt:message key="login.Password"/> </label>
    <input id="password-input" name="password" type="password">
    <br/>
    <input type="submit" value="LOGIN">
</form>
</body>
</html>