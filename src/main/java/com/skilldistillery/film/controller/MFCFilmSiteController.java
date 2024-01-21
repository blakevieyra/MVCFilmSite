package com.skilldistillery.film.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class MFCFilmSiteController {

	@Autowired
	private FilmDAO filmDao;
	{
	}

	@RequestMapping
	private String home() {
		return "WEB-INF/views/home.jsp";
	}

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		filmDao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createFilm");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		filmDao.updateFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateFilm");
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Film film) {
		filmDao.deleteFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deleteFilm");
		return mv;
	}

	@RequestMapping(path = "searchFilmById.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmByID(@RequestParam("id") int filmID) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.searchFilmById(filmID);
		mv.addObject("film", film);
		mv.setViewName("showFilmByID");
		return mv;
	}

	@RequestMapping(path = "searchFilmByKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView searchFilmByKeyword(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> film = filmDao.searchFilmByKeyword(keyword);
		mv.addObject("film", film);
		mv.setViewName("showFilmByKeyword");
		return mv;
	}
}