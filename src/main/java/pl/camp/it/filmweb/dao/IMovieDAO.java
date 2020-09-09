package pl.camp.it.filmweb.dao;

import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import java.util.List;

public interface IMovieDAO {
    List<Movie> getAllMovies();
    List<Director> getAllDirectors();
    void addMovie(Movie movie);
    Director getDirectorByNameAndSurname(String name, String surname);
    List<Movie> getMoviesByGenre(Movie.Genre genre);
    List<Movie> getMoviesByPattern(String pattern);
    List<Director> getDirectorsByPattern(String pattern);
    List<Movie> getMoviesByDirectorId(int id);
    List<Movie> getMovieByFilter(String pattern, Movie.Genre genre, String productionYear);
}
