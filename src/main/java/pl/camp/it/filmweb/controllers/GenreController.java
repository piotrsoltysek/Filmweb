package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.List;


@Controller
public class GenreController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @RequestMapping(value = "/action", method = RequestMethod.GET)
    public String actionFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.ACTION);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/action");
        return "main";
    }


    @RequestMapping(value = "/drama", method = RequestMethod.GET)
    public String dramaFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.DRAMA);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/drama");
        return "main";
    }
}
