package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film searchFilmById(int filmId) throws SQLException;

	List<Film> searchFilmByKeyword(String keyword) throws SQLException;

	List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	Film createFilm(Film film) throws SQLException;

	boolean updateFilm(Film film) throws SQLException;

	boolean deleteFilm(Film film) throws SQLException;

//	public Film findFilmById(int filmId) throws SQLException {
//	    Connection conn = null;
//	    PreparedStatement stmt = null;
//	    ResultSet rs = null;
//	    Film film = null;
//
//	    try {
//	        conn = dataSource.getConnection();
//	        String sql = "SELECT * FROM film WHERE id = ?";
//	        stmt = conn.prepareStatement(sql);
//	        stmt.setInt(1, filmId);
//	        rs = stmt.executeQuery();
//
//	        if (rs.next()) {
//	            film = new Film();
//	            film.setId(rs.getInt("id"));
//	            film.setTitle(rs.getString("title"));
//	            // Set other properties of the film based on your database columns
//	        }
//	    } finally {
//	        // Close the database resources
//	        if (rs != null) {
//	            rs.close();
//	        }
//	        if (stmt != null) {
//	            stmt.close();
//	        }
//	        if (conn != null) {
//	            conn.close();
//	        }
//	    }
//
//	    return film;
//	}
}
