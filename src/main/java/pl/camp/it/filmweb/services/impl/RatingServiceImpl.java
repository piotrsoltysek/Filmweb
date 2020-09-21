package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Rating;
import pl.camp.it.filmweb.model.User;
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

    @Autowired
    IMovieDAO movieDAO;

    @Override
    public void addRating(Rating rating, int movieId) {
        Movie movie = this.movieService.findMovieById(movieId);
        if (!alreadyRated(this.sessionObject.getUser(), movie)) {
            rating.setMovie(movie);
            rating.setUser(this.sessionObject.getUser());
            this.ratingDAO.addRating(rating);
            List<Rating> ratings = this.ratingDAO.getRatingByMovieId(movieId);
            movie.setAverage(this.getMovieAverageRating(ratings));
            this.movieDAO.updateMovie(movie);
        }
    }

    @Override
    public double getMovieAverageRating(List<Rating> ratings) {
        double sum = 0.0;
        double numbers = 0.0;

        for (Rating rating : ratings) {
            sum += rating.getScore();
            numbers++;
        }
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double average =  sum / numbers * 100;
        int averageInt = (int) average;
        double newAverage = 1.0 * averageInt / 100;
        return newAverage;
    }

    @Override
    public boolean alreadyRated(User user, Movie movie) {
        if (this.ratingDAO.getUserRate(user.getId(), movie.getId()).isEmpty()) {
            return false;
        }
        return true;
    }
}
