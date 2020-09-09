package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Review;

public interface IReviewService {
    void addReview(Review review, int movieId);
}
