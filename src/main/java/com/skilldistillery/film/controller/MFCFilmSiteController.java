package com.skilldistillery.film.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@RequestMapping(path="")
	private String home() {
		return "WEB-INF/views/home.jsp";
	}
	

	
	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) throws SQLException {
		filmDao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/confirmationPage.jsp");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) throws SQLException {
		filmDao.updateFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/views/confirmationPage.jsp");
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("id") int filmId) throws SQLException {
	    ModelAndView mv = new ModelAndView();
	    
	    // Check if the film exists in your database based on the filmId
	    Film filmToDelete = filmDao.searchFilmById(filmId); // Modify this line to match your actual DAO method
	    
	    if (filmToDelete != null) {
	        boolean deleted = filmDao.deleteFilm(filmToDelete); // Modify this line to match your actual DAO method
	    
	        if (deleted) {
	            // If deletion is successful, redirect to the confirmation page
	    		mv.setViewName("WEB-INF/views/confirmationPage.jsp");
	        } else {
	            // If deletion fails, redirect to an error page
	            mv.setViewName("redirect:/errorPage");
	        }
	    } else {
	        // If the film doesn't exist, redirect to an error page
	        mv.setViewName("redirect:/errorPage");
	    }
	    
	    return mv;
	}

	

	@RequestMapping(path = "searchFilmById.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmByID(@RequestParam("id") String filmID) throws SQLException {
	    ModelAndView mv = new ModelAndView();

	    // Validate the film ID
	    int id;
	    try {
	        id = Integer.parseInt(filmID);

	        if (id <= 0) {
	            // Invalid film ID (less than or equal to 0)
	            mv.addObject("message", "Invalid Film ID");
	            mv.setViewName("WEB-INF/views/showFilmByID.jsp");
	            return mv;
	        }
	    } catch (NumberFormatException e) {
	        // Invalid film ID (not a valid integer)
	        mv.addObject("message", "Invalid Film ID");
	        mv.setViewName("WEB-INF/views/showFilmByID.jsp");
	        return mv;
	    }

	    Film film = filmDao.searchFilmById(id);

	    if (film != null) {
	        // Film found, set it as an attribute
	        mv.addObject("film", film);
	    } else {
	        // Film not found, set a message
	        mv.addObject("message", "No Film Found");
	    }

	    mv.setViewName("WEB-INF/views/showFilmByID.jsp");
	    return mv;
	}




	@RequestMapping(path = "searchFilmByKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView searchFilmByKeyword(@RequestParam("keyword") String keyword) throws SQLException {
		ModelAndView mv = new ModelAndView();
		List<Film> film = filmDao.searchFilmByKeyword(keyword);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/views/showFilmByKeyword.jsp");
		return mv;
	}
	@Controller
	public class ConfirmationController {

	    @GetMapping("/confirmation")
	    public ModelAndView showConfirmationPage() {
	        ModelAndView mv = new ModelAndView();

	        mv.setViewName("WEB-INF/views/confirmationPage.jsp");
	        return mv;
	    }
	}

}