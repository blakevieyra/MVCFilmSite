package com.skilldistillery.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		mv.setViewName("result");
		return mv;
	}

	public String deleteFilm(Film film, RedirectAttributes redir) {
		filmDao.deleteFilm(film);
		redir.addFlashAttribute("film", film);
		return "redirect:deleteFilm.do";
	}

	@RequestMapping("showFilms.do")
	public ModelAndView stateAdded() {
		ModelAndView mv = new ModelAndView();
		// This uses InternalResourceViewResolver with WEB-INF and .jsp as the prefix
		// and suffix
		mv.setViewName("result");
		return mv;

	}
}