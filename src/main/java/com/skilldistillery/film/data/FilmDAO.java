package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film searchFilmById(int filmId);

	List<Film> searchFilmByKeyword(String keyword);

	List<Actor> findActorsByFilmId(int filmId);

	Film createFilm(Film film);

	boolean updateFilm(Film film);

	boolean deleteFilm(Film film);

}
