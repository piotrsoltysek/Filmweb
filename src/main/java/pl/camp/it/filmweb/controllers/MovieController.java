package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Rating;
import pl.camp.it.filmweb.model.Review;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.services.IReviewService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;


@Controller
public class MovieController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @Autowired
    IReviewService reviewService;

    @Autowired
    IRatingService ratingService;


    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String showAddMovie(Model model) {
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        model.addAttribute("movie", new Movie());
        model.addAttribute("director", new Director());
        return "addMovie";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute Movie movie,
                           @ModelAttribute Director director) {
        this.movieService.addMovie(movie, director);
        return "redirect:/main";
    }

    @RequestMapping(value = "/addReview/{movieId}", method = RequestMethod.GET)
    public String showAddReview(@PathVariable int movieId, Model model) {
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        model.addAttribute("review", new Review());
        model.addAttribute("movieId", movieId);

        return "addReview";
    }

    @RequestMapping(value = "/addReview/{movieId}", method = RequestMethod.POST)
    public String addReview(@ModelAttribute Review review, @PathVariable int movieId) {
        this.reviewService.addReview(review, movieId);
        return "redirect:" + this.sessionObject.getLastAddress();
    }

    @RequestMapping(value = "/addRating/{movieId}", method = RequestMethod.GET)
    public String showAddRating(@PathVariable int movieId, Model model) {
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        model.addAttribute("rating", new Rating());
        model.addAttribute("movieId", movieId);

        return "addRating";
    }

    @RequestMapping(value = "/addRating/{movieId}", method = RequestMethod.POST)
    public String addRating(@ModelAttribute Rating rating, @PathVariable int movieId) {
        this.ratingService.addRating(rating, movieId);
        return "redirect:" + this.sessionObject.getLastAddress();
    }

    @RequestMapping(value = "/reviews/{movieId}", method = RequestMethod.GET)
    public String showMovieReview(@PathVariable int movieId, Model model) {
        Movie movie = this.movieService.findMovieById(movieId);
        List<Review> reviews = this.reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));
        model.addAttribute("movieId", movieId);
        model.addAttribute("reviews", reviews);

        return "reviews";
    }


    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("isLogged", (this.sessionObject.getUser() != null));

        List<Movie> movies = this.movieService.getAllMovies();
        movies.sort(Comparator.comparingDouble(Movie::getAverage).reversed());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/main");
        return "main";
    }
}



