<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <title>Create user</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>Create user</h1>

<form:form
        method="post"
        action="${pageContext.request.contextPath}/users/create"
        modelAttribute="user">

    <form:errors path="*" cssClass="error"/>

    <div>
        <label for="input-email">Email</label>

        <form:input
                id="input-email"
                path="email"
                type="email"
                required="required"/>

        <form:errors path="email" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-password">Password</label>

        <form:password
                id="input-password"
                path="password"
                required="required"/>

        <form:errors path="password" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-role">Role</label>

        <form:select
                id="input-role"
                path="role"
                required="required">

            <form:option value="GUEST" label="GUEST"/>
            <form:option value="USER" label="USER"/>
            <form:option value="MANAGER" label="MANAGER"/>
            <form:option value="ADMIN" label="ADMIN"/>

        </form:select>

        <form:errors path="role" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-first-name">First name</label>

        <form:input
                id="input-first-name"
                path="firstName"/>

        <form:errors path="firstName" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-last-name">Last name</label>

        <form:input
                id="input-last-name"
                path="lastName"/>

        <form:errors path="lastName" cssClass="error"/>
    </div>

    <br/>

    <button type="submit">Create</button>

</form:form>

<br/>

<a href="${pageContext.request.contextPath}/users/getAll">
    Back to users
</a>

</body>
</html>