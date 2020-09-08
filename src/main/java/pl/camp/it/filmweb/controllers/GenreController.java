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
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.ACTION);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/action");
        return "main";
    }

    @RequestMapping(value = "/comedy", method = RequestMethod.GET)
    public String comedyFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.COMEDY);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/comedy");
        return "main";
    }

    @RequestMapping(value = "/drama", method = RequestMethod.GET)
    public String dramaFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.DRAMA);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/drama");
        return "main";
    }

    @RequestMapping(value = "/fantasy", method = RequestMethod.GET)
    public String fantasyFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.FANTASY);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/fantasy");
        return "main";
    }

    @RequestMapping(value = "/horror", method = RequestMethod.GET)
    public String horrorFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.HORROR);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/horror");
        return "main";
    }

    @RequestMapping(value = "/romance", method = RequestMethod.GET)
    public String romanceFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.ROMANCE);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/romance");
        return "main";
    }

    @RequestMapping(value = "/thriller", method = RequestMethod.GET)
    public String thrillerFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.THRILLER);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/thriller");
        return "main";
    }

    @RequestMapping(value = "/western", method = RequestMethod.GET)
    public String westernFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.WESTERN);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/western");
        return "main";
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public String otherFilms(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.getMoviesByGenre(Movie.Genre.OTHER);
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/other");
        return "main";
    }
}
