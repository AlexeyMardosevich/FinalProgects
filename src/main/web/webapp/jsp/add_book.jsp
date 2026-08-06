<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new book</title>
    <link rel="stylesheet" href="css/style.scc">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Add new book</h1>
<form action="controller?command=add_book" method="post">
    <label for="input-name">Name</label>
    <input id="input-name" type="text" name="name">
    <label for="input-author">Author</label>
    <input id="input-author" type="text" name="author">
    <label for="input-price">Price</label>
    <input id="input-price" type="number" name="price">
    <br/>
    <input type="submit" name="Create">
</form>
</body>
</html>
