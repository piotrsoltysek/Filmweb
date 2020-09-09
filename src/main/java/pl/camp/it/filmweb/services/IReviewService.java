package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Review;

import java.util.List;

public interface IReviewService {
    void addReview(Review review, int movieId);
    List<Review> getReviewsByMovieId(int id);
}
