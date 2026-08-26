<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="msg"/>

<html>
<head>
    <title>
        <fmt:message bundle="${msg}" key="addBook.Title"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>

<body>

<jsp:include page="navbar.jsp"/>

<h1>
    <fmt:message bundle="${msg}" key="addBook.AddNewBook"/>
</h1>

<form:form method="post"
           action="${pageContext.request.contextPath}/books/create"
           modelAttribute="book">

    <form:errors path="*" cssClass="error"/>

    <div>
        <label for="input-name">
            <fmt:message bundle="${msg}" key="addBook.Name"/>
        </label>

        <form:input
                id="input-name"
                path="name"
                type="text"
                required="required"/>

        <form:errors path="name" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-author">
            <fmt:message bundle="${msg}" key="addBook.Author"/>
        </label>

        <form:input
                id="input-author"
                path="author"
                type="text"
                required="required"/>

        <form:errors path="author" cssClass="error"/>
    </div>

    <br/>

    <div>
        <label for="input-price">
            <fmt:message bundle="${msg}" key="addBook.Price"/>
        </label>

        <form:input
                id="input-price"
                path="price"
                type="number"
                step="0.01"
                min="0.01"
                required="required"/>

        <form:errors path="price" cssClass="error"/>
    </div>

    <br/>

    <button type="submit">
        <fmt:message bundle="${msg}" key="addBook.Create"/>
    </button>

</form:form>

</body>
</html>