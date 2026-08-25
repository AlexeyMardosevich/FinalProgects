<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="messages"/>
<fmt:setLocale value="${sessionScope.locale}"/>

<ul class="navbar">
    <li>
        <a href="${pageContext.request.contextPath}/books/getAll">Books</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/books/create">Add book</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/users/getAll">Users</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/users/create">Add user</a>
    </li>
</ul>