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
		Please enter a film title.
		<label for="title">Title:</label> 
		<input type="text" name="title"> <br> 
		
		Please enter a film description.
		<label for="description">Description:</label>
		<input type="text" name="description"> <br> 
		
		Please input a release year in YYYY format.
		<label for="releaseYear">Release Year:</label> 
		<input type="number" name="releaseYear"> <br> 
		
		Please input a rental duration in whole numbers.
		<label for="rentalDuration">Rental Duration:</label>
		<input type="number" name="rentalDuration""> <br> 
		
		Please input a replacement cost in dollars and cents.
		<label for="rentalRate">Rental Rate:</label> 
		<input type="number" name="rentalRate"> <br> 
		
		Please input a length using whole numbers.
		<label for="length">Length:</label>
		<input type="number" name="length"> <br> 
		
		Please input a replacement cost in dollars and cents.
		<label for="replacementCost">Replacement Cost:</label> 
		<input type="number" name="eplacementCost"> <br> 
		
		Please input from the following rating: G, PG, PG13, R, N17.
		<label for="length">Rating:</label>
		<input type="number" name="length"> <br> 
	</form>
</body>
</html>