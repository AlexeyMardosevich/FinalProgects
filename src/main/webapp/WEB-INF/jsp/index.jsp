<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<c:if test="${sessionScope.lang != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="home.Name"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Bookstore</h1>
<img src="images/bookstore.png" alt="Bookstore">
<c:if test="${sessionScope.user != null}">
    <h2><fmt:message key="home.Welcome"/>${sessionScope.user.firstName && sessionScope.user.lastName}!</h2>
</c:if>
<c:if test="${sessionScope.user == null}">
<h2><fmt:message key="home.WelcomeGuest"/></h2>
</c:if>
    <a href="controller?command=books"><fmt:message key="home.Catalog"/></a>
</body>
</html>