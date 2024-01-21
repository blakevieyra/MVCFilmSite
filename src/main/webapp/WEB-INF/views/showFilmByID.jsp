<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Film by ID</title>
</head>
<body>
    <p>
        <a href=".">Return Home</a>
    </p>
    
    <form action="deleteFilm.do" method="POST" id="deleteFilmForm">
        <input type="hidden" name="id" id="filmIdToDelete">
        <button type="button" id="deleteButton" class="btn btn-danger">DELETE</button>
    </form>
    
    <c:choose>
        <c:when test="${! empty film}">
            <p>${film}</p>
        </c:when>
        <c:otherwise>
            <p>${message}</p>
        </c:otherwise>
    </c:choose>
    
    <p>
        <a href=".">Return to Home Page</a>
    </p>
    
    <script>
        // Add JavaScript to set the film ID when the delete button is clicked
        document.getElementById("deleteButton").addEventListener("click", function() {
            var filmId = "${film.id}"; // Replace with the actual film ID
            document.getElementById("filmIdToDelete").value = filmId;
            document.getElementById("deleteFilmForm").submit();
        });
    </script>
    
</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</html>