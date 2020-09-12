package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public void addRating(Rating rating, int movieId) {
        if (!alreadyRated(this.sessionObject.getUser(), this.movieService.findMovieById(movieId))) {
            rating.setMovie(this.movieService.findMovieById(movieId));
            rating.setUser(this.sessionObject.getUser());
            this.ratingDAO.addRating(rating);
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
    public List<Movie> setAverageToMovies(List<Movie> movies) {
        for (Movie tempMovie : movies) {
            tempMovie.setAverage(this.getMovieAverageRating(this.ratingDAO.getRatingByMovieId(tempMovie.getId())));
        }
        return movies;
    }

    @Override
    public boolean alreadyRated(User user, Movie movie) {
        if (this.ratingDAO.getUserRate(user.getId(), movie.getId()).isEmpty()) {
            return false;
        }
        return true;
    }
}
