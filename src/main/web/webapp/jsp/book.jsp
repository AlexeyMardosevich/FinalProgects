<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><Book</title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Book</h1>
<h2>Id</h2>
<p>${book.id}</p>
<h2>Name</h2>
<p>${book.name}</p>
<h2>Author</h2>
<p>${book.author}</p>
<h2>Price</h2>
<p>${book.price}</p>
</body>
</html>