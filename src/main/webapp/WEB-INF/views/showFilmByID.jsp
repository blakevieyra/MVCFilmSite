<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Film by ID</title>
</head>
<body>
    <c:choose>
        <c:when test="${! empty film}">
            <ul>
                <li>${film}</li>
            </ul>
        </c:when>
        <c:otherwise>
            <p>${message}</p>
        </c:otherwise>
    </c:choose>
    <p>
        <a href=".">Return to Home Page</a>
    </p>
</body>
</html>
