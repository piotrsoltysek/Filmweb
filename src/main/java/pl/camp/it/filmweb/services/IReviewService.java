package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Review;
import pl.camp.it.filmweb.model.User;

import java.util.List;

public interface IReviewService {
    void addReview(Review review, int movieId);
    List<Review> getReviewsByMovieId(int id);
    boolean alreadyReviewed(User user, Movie movie);

}
