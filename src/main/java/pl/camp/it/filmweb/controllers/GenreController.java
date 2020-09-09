package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.services.IReviewService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.List;


@Controller
public class GenreController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @Autowired
    IReviewService reviewService;

    @Autowired
    IRatingService ratingService;

    @Autowired
    IRatingDAO ratingDAO;


    @RequestMapping(value = "/action", method = RequestMethod.GET)
    public String actionFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.ACTION);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/action");
        return "main";
    }


    @RequestMapping(value = "/comedy", method = RequestMethod.GET)
    public String comedyFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.COMEDY);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/comedy");
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

    @RequestMapping(value = "/fantasy", method = RequestMethod.GET)
    public String fantasyFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.FANTASY);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/fantasy");
        return "main";
    }

    @RequestMapping(value = "/horror", method = RequestMethod.GET)
    public String horrorFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.HORROR);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/horror");
        return "main";
    }

    @RequestMapping(value = "/romance", method = RequestMethod.GET)
    public String romanceFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.ROMANCE);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/romance");
        return "main";
    }

    @RequestMapping(value = "/thriller", method = RequestMethod.GET)
    public String thrillerFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.THRILLER);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/thriller");
        return "main";
    }

    @RequestMapping(value = "/western", method = RequestMethod.GET)
    public String westernFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.WESTERN);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.ratingService.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/western");
        return "main";
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public String otherFilms(Model model) {
        this.sessionObject.getUserFilter().setGenre(Movie.Genre.OTHER);
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        List<Movie> movies = this.movieService.findMoviesByFilter(sessionObject.getUserFilter());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/other");
        return "main";
    }
}
