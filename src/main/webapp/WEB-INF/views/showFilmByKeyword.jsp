<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film by keyword</title>
</head>
<body>
    <c:choose>
      <c:when test="${! empty film}  ">
        <ul>
          <li>${film.title}</li>
          <li>${film.description}</li>
          <li>${film.rentalRate}</li>
          <li>${film.rating}</li>
          <li>${film.rentalDuration}</li>
          <li>${film.language}</li>
        </ul>
      </c:when>
      <c:otherwise>
        <p>No Film Found</p>
      </c:otherwise>
    </c:choose>
    <p>
		<a href="WEB-INF/views/home.jsp">Return Home</a>
	</p>
	
  </body>
</html>