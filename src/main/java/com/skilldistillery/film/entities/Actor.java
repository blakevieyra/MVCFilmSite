package com.skilldistillery.film.entities;

import java.util.List;
import java.util.Objects;

public class Actor {

	private int actorId;
	private String firstName;
	private String lastName;
	private List<Film> films;

	public Actor() {
	}

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor(int actorId, String firstName, String lastName) {
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor(int actorId, String firstName, String lastName, List<Film> films) {
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.films = films;
	}

	public int getId() {
		return actorId;
	}

	public void setId(int id) {
		this.actorId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(Film film) {
		this.films.add(film);
	}

	@Override
	public String toString() {
		return "" + firstName + " " + lastName + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(films, firstName, actorId, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(films, other.films) && Objects.equals(firstName, other.firstName)
				&& actorId == other.actorId && Objects.equals(lastName, other.lastName);
	}

}
