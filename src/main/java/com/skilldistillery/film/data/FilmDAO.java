package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film findFilmById(int filmId);

	List<Film> searchByKeyword(String keyword);

	List<Actor> findActorsByFilmId(int filmId);

	Film createFilm(Film film);

	boolean saveFilm(Film film);

	boolean deleteFilm(Film film);

}
