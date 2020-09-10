package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Rating;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.session.SessionObject;

import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    IMovieService movieService;

    @Autowired
    SessionObject sessionObject;

    @Autowired
    IRatingDAO ratingDAO;

    @Override
    public void addRating(Rating rating, int movieId) {
        rating.setMovie(this.movieService.findMovieById(movieId));
        rating.setUser(this.sessionObject.getUser());
        this.ratingDAO.addRating(rating);
    }

    @Override
    public double getMovieAverageRating(List<Rating> ratings) {
        int sum = 0;
        int numbers = 0;

        for (Rating rating : ratings) {
            sum += rating.getScore();
            numbers++;
        }
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return 1.0 * sum / numbers;
    }

    @Override
    public List<Movie> addRatingsToMovies(List<Movie> movies) {
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        return movies;
    }
}
