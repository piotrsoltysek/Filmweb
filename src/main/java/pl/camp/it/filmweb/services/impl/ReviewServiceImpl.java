package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IReviewDAO;
import pl.camp.it.filmweb.model.Review;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IReviewService;
import pl.camp.it.filmweb.session.SessionObject;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    IMovieService movieService;

    @Autowired
    SessionObject sessionObject;

    @Autowired
    IReviewDAO reviewDAO;


    @Override
    public void addReview(Review review, int movieId) {
        review.setMovie(this.movieService.findMovieById(movieId));
        review.setUser(this.sessionObject.getUser());
        this.sessionObject.getUser().getReviews().add(review);
        this.movieService.findMovieById(movieId).getReviews().add(review);
        this.reviewDAO.addReview(review);
    }
}
