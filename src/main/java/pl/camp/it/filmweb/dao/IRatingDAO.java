package pl.camp.it.filmweb.dao;

import pl.camp.it.filmweb.model.Rating;

import java.util.List;

public interface IRatingDAO {
    void addRating(Rating rating);
    List<Rating> getRatingByMovieId(int id);
    List<Rating> getUserRate(int userId, int movieId);

}
