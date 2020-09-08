package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;


@Controller
public class MovieController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String showAddMovie(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        model.addAttribute("movie", new Movie());
        model.addAttribute("director", new Director());
        return "addMovie";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute Movie movie,
                           @ModelAttribute Director director) {
        sessionObject.getUser().addMovie(movie);
        movieService.addMovie(movie, director);
        return "redirect:/main";
    }
}




