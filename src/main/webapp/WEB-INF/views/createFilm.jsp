<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a new film</title>
</head>
<body>
	<form action="addFilm.do" method="POST">
		<label for="id">Film ID:</label> <input type="number" name="id">
		<br> <label for="title">Title:</label> <input type="text"
			name="title"> <br> <label for="description">description:</label>
		<input type="text" name="description"> <br> <input
			type="submit" value="Add Film"> <input type="text"
			name="description"> <br> <input type="submit"
			value="Add Film"> <input type="text" name="description">
		<br> <input type="submit" value="Add Film"> <input
			type="text" name="description"> <br> <input
			type="submit" value="Add Film"> <input type="text"
			name="description"> <br> <input type="submit"
			value="Add Film"> <input type="text" name="description">
		<br> <input type="submit" value="Add Film">
	</form>
</body>
</html>