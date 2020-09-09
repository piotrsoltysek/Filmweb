package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.model.Rating;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.session.SessionObject;

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
}
