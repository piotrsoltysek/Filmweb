package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Rating;

import java.util.List;

public interface IRatingService {
    void addRating(Rating rating, int movieId);
    double getMovieAverageRating(List<Rating> ratings);
    List<Movie> addRatingsToMovies(List<Movie> movies);
}