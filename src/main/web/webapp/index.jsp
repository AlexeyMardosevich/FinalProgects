<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bookstore - Main</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="jsp/navbar.jsp"/>
<h1>Bookstore</h1>
<img src="images/bookstore.png" alt="Bookstore">
    <h2>Welcome to bookstore, dear Guest!</h2>
    <a href="controller?command=books">Catalog</a>
</body>
</html>