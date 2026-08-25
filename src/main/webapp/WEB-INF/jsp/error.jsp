<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<html>
<head>
    <title><fmt:message key="error.ErrorPage"/></title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="error.Error"/></h1>
<p><fmt:message key="error.MessageError"/></p>
<p>${requestScope.massage}</p>
</body>
</html>