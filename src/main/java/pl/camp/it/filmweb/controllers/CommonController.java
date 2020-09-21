package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


@Controller
public class CommonController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @Autowired
    IRatingService ratingService;



    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        this.sessionObject.getUserFilter().reset();

        List<Movie> movies = this.movieService.getAllMovies();
        movies.sort(Comparator.comparing(Movie::getTitle));
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
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        movies.sort(Comparator.comparing(Movie::getTitle));
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/find");
        return "main";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findMovies(Model model, @RequestParam String pattern, @RequestParam String productionYear) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        if (!pattern.equals("")) {
            this.sessionObject.getUserFilter().setLastFindPattern(pattern);
        }
        if (!productionYear.equals("")) {
            this.sessionObject.getUserFilter().setProductionYear(productionYear);
        }
        List<Movie> movies = this.movieService.findMoviesByFilter(this.sessionObject.getUserFilter());
        movies.sort(Comparator.comparing(Movie::getTitle));
        this.sessionObject.getUserFilter().setLastFindPattern(pattern);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/find");
        return "main";
    }
}