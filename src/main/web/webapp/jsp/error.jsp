<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><Error Page</title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Error</h1>
<p>Something went wrong!</p>
<p>${requestScope.massage}</p>
</body>
</html>