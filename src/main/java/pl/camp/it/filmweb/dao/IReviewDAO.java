package pl.camp.it.filmweb.dao;

import pl.camp.it.filmweb.model.Review;

import java.util.List;

public interface IReviewDAO {
    void addReview(Review review);
    List<Review> getAllReviews();
    List<Review> getReviewsByUserId(int id);
    List<Review> getReviewsByMovieId(int id);
}
