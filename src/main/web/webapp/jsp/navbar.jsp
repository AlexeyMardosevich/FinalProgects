<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar">
    <ul>
        <li><a href="">Home</a> </li>
        <li><a href="controller?command=books">Catalog</a> </li>
        <li><a href="controller?command=books">All user</a> </li>
        <c:if test="${sessionScope.user != null && sessionScope.user.role.toString() == 'ADMIN'}">
        <li><a href="controller?command=add_book_form">Add new book</a> </li>
        </c:if>
        <c:if test="${sessionScope.user == null}">
        <li><a href="controller?command=login_form">Log In</a> </li>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <li><a href="controller?command=logout">Log Out</a> </li>
        </c:if>
    </ul>
</nav>