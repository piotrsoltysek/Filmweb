package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.List;


@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @Autowired
    IRatingService ratingService;

    @Autowired
    IRatingDAO ratingDAO;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        sessionObject.getUserFilter().reset();

        List<Movie> movies = this.movieService.getAllMovies();
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.ratingService.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/main");
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultRedirect() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findMovies(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.ratingService.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/find");
        return "main";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findMovies(Model model, @RequestParam String pattern) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        sessionObject.getUserFilter().setLastFindPattern(pattern);
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.ratingService.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        this.sessionObject.getUserFilter().setLastFindPattern(pattern);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/find");
        return "main";
    }
}