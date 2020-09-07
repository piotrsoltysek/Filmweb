package pl.camp.it.filmweb.dao;

import pl.camp.it.filmweb.model.Movie;
import java.util.List;

public interface IMovieDAO {
    List<Movie> getAllMovies();
    void addMovie(Movie movie);
}
