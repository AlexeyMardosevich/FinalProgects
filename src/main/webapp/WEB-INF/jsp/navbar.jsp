<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<nav class="navbar">

    <div class="navbar-brand">
        <a href="${pageContext.request.contextPath}/books/getAll">
            <fmt:message bundle="${msg}" key="navbar.Store"/>
        </a>
    </div>

    <div class="navbar-links">

        <a href="${pageContext.request.contextPath}/books/getAll">
            <fmt:message bundle="${msg}" key="navbar.Books"/>
        </a>

        <a href="${pageContext.request.contextPath}/cart">
            <fmt:message bundle="${msg}" key="navbar.Cart"/>
        </a>

        <a href="${pageContext.request.contextPath}/users/getAll">
            <fmt:message bundle="${msg}" key="navbar.Users"/>
        </a>

        <a href="${pageContext.request.contextPath}/books/create">
            <fmt:message bundle="${msg}" key="navbar.AddBook"/>
        </a>

    </div>

    <div class="navbar-auth">

        <c:choose>

            <c:when test="${not empty sessionScope.userId}">
                <span>
                    <fmt:message bundle="${msg}" key="navbar.User"/>
                    ID: ${sessionScope.userId}
                </span>

                <form
                        action="${pageContext.request.contextPath}/logout"
                        method="post"
                        style="display: inline">

                    <button type="submit">
                        <fmt:message bundle="${msg}" key="navbar.Logout"/>
                    </button>
                </form>
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">
                    <fmt:message bundle="${msg}" key="navbar.Login"/>
                </a>
            </c:otherwise>

        </c:choose>

    </div>

</nav>