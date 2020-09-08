package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    void addMovie(Movie movie, Director director);
    boolean directorInDB(Director director);
}
