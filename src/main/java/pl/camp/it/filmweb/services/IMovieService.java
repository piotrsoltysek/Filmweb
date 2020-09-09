package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.filter.UserFilter;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    void addMovie(Movie movie, Director director);
    boolean directorInDB(Director director);
    List<Movie> findMoviesByGenre(Movie.Genre genre);
    List<Movie> findMoviesByPattern(String pattern);
    List<Movie> findMoviesByPatternAndGenre(String pattern, Movie.Genre genre);
    List<Movie> findMoviesByFilter(UserFilter userFilter);
    List<Movie> findMoviesByUserId(int id);
}
