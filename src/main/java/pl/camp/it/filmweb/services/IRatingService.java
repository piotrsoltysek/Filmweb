package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Rating;

public interface IRatingService {
    void addRating(Rating rating, int movieId);
}
