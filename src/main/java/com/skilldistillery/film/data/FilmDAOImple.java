package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Repository
public class FilmDAOImple implements FilmDAO {

	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	public FilmDAOImple() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		FilmDAOImple start = new FilmDAOImple();
		start.validateConn();
	}

	private Connection validateConn() throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}

	@Override
	public Film createFilm(Film film) throws SQLException {
		Connection conn = validateConn();
		try {
			conn.setAutoCommit(false); // START TRANSACTION

			// Check for duplicate titles in a case-insensitive manner
			String checkSql = "SELECT COUNT(*) FROM film WHERE UPPER(title) = ?";
			PreparedStatement checkStmt = conn.prepareStatement(checkSql);
			checkStmt.setString(1, film.getTitle().toUpperCase());
			ResultSet resultSet = checkStmt.executeQuery();
			resultSet.next();
			int rowCount = resultSet.getInt(1);
			checkStmt.close();

			if (rowCount > 0) {
				// Duplicate title found, handle it accordingly (e.g., return a custom error
				// message)
				film.setError("Film with the same title already exists.");
				return film; // Return the Film object with the error message
			}

			// If no duplicate title found, proceed with the insertion
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, "
					+ "rental_rate, length, replacement_cost, rating, special_features) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Convert the title to uppercase before inserting
			String title = film.getTitle().toUpperCase();
			stmt.setString(1, title);
			stmt.setString(2, film.getDescription());
			stmt.setShort(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1);
					film.setId(generatedId);
				} else {
					conn.rollback(); // ROLLBACK TRANSACTION
					throw new SQLException("Insertion failed, no generated ID obtained.");
				}
				conn.commit(); // COMMIT TRANSACTION
			} else {
				conn.rollback(); // ROLLBACK TRANSACTION
				throw new SQLException("Insertion failed, no rows affected.");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback(); // ROLLBACK TRANSACTION
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting film " + film, sqle);
		} finally {
			if (conn != null) {
				conn.setAutoCommit(true); // Reset auto-commit to true
			}
		}
		return film;
	}

	@Override
	public boolean updateFilm(Film film) throws SQLException {

		Connection conn = validateConn();
		try {
			conn.setAutoCommit(false);
			// START TRANSACTION
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id  = ?,"
					+ "rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?"
					+ " WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setShort(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE film_actor.film_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());

				updateCount = stmt.executeUpdate();
			}
			conn.commit(); // COMMIT TRANSACTION

		} catch (

		SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteFilm(Film film) throws SQLException {
		Connection conn = validateConn();
		try {
			conn.setAutoCommit(false);
			// START TRANSACTION

			// deleteing foreign key children before deleting parent key, hope to resolve
			// the
			// issue
			String sql = "DELETE FROM film_actor WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM film_category WHERE film_category.film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			updateCount = stmt.executeUpdate();
			
			sql = "DELETE rental\n"
					+ "FROM rental\n"
					+ "JOIN inventory_item ON rental.inventory_id = inventory_item.id\n"
					+ "WHERE inventory_item.film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			updateCount = stmt.executeUpdate();
			
			sql = "DELETE FROM inventory_item WHERE inventory_item.film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			updateCount = stmt.executeUpdate();

//			sql = "DELETE FROM inventory_item WHERE inventory_item.id = ?";
//			stmt = conn.prepareStatement(sql);
//			// stmt.setInt(1, film.getId());
//
//			updateCount = stmt.executeUpdate();
//java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`sdvid`.`rental`, CONSTRAINT `fk_rental_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `inventory_item` (`id`) ON UPDATE CASCADE)
//keep getting this error
//			sql = "DELETE FROM rental WHERE rental.inventory_id = ?";
//			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, film.getId());

			updateCount = stmt.executeUpdate();

			sql = "DELETE FROM film WHERE film.id= ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			updateCount = stmt.executeUpdate();
			if (updateCount != 1) {
				return false;
			}
			conn.commit(); // COMMIT TRANSACTION

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public Film searchFilmById(int filmId) throws SQLException {
		Connection conn = validateConn();
		Film film = null;
		List<Actor> actors = new ArrayList<>();
		try {
			String sql = "SELECT * FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				filmId = rs.getInt("film.id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("language.name");
				actors.addAll(findActorsByFilmId(filmId));
				film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, language, actors);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> searchFilmByKeyword(String keyword) throws SQLException {
		Connection conn = validateConn();
		List<Film> films = new ArrayList<>();
		Film film = null;

		try {
			String sql = "SELECT * FROM film JOIN language ON film.language_id  = language.id WHERE film.title LIKE ? OR film.description LIKE ? ORDER BY film.title";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("film.id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				String language = rs.getString("language.name");
				film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, language, findActorsByFilmId(filmId));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		Connection conn = validateConn();
		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;
		try {
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ";
			sql += "ON film.id = film_actor.film_id WHERE film_id = ?";
			/*
			 * The 2 lines above replace the 2 lines below. The error message and stack
			 * trace indicated that an exception was occurring within the searchFilmById
			 * method of the code. The specific error is related to parsing a string as a
			 * number when retrieving data from the database.
			 */
//			String sql = "SELECT actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ";
//			sql += "ON film.id = film_actor.film_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				actor = new Actor(id, firstName, lastName);
				actorList.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

}
