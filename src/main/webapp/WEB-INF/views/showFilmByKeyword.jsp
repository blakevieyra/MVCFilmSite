<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film by keyword</title>
</head>
<body>
<h1>Search Results=</h1>
<p>
	<a href=".">Return Home</a>
</p>
<p>
	<a href="searchFilm.html">Search Again</a>
</p>

<c:if test="${not empty film}">
    <form action="deleteFilm.do" method="POST">
        Please enter the film ID of the film you want to delete. <br><label
            for="id">ID:</label> <input type="text" name="id"> <input
            type="submit" value="DELETE"> <br>
    </form>
    <form action="updateFilm.do" method="POST">
        Please enter the film ID of the film you want to update. <br><label
            for="updateId">ID:</label> <input type="text" name="updateId"> <input
            type="submit" value="UPDATE"> <br>
    </form>
</c:if>

<c:choose>
    <c:when test="${not empty film}">
        <p>${film}</p>
    </c:when>
    <c:otherwise>
        <p>No results found.</p>
    </c:otherwise>
</c:choose>

<p>
	<a href=".">Return Home</a>
</p>

</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>
