<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="book.Title"/></title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1><fmt:message key="book.Book"/></h1>
<h2><fmt:message key="book.Id"/></h2>
<p>${book.id}</p>
<h2><fmt:message key="book.Name"/></h2>
<p>${book.name}</p>
<h2><fmt:message key="book.Author"/></h2>
<p>${book.author}</p>
<h2><fmt:message key="book.Price"/></h2>
<p>${book.price}</p>
</body>
</html>