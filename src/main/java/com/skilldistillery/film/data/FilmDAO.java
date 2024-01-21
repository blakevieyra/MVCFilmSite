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

}
