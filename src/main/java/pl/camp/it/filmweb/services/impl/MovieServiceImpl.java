package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.filter.UserFilter;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.session.SessionObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    IMovieDAO movieDAO;

    @Autowired
    SessionObject sessionObject;


    @Override
    public List<Movie> getAllMovies() {
        return this.movieDAO.getAllMovies();
    }

    @Override
    public void addMovie(Movie movie, Director director) {
        if (directorInDB(director)) {
            movie.setDirector(movieDAO.getDirectorByNameAndSurname(director.getName(), director.getSurname()));
        } else {
            movie.setDirector(director);
        }
        movie.setUser(sessionObject.getUser());
        System.out.println("movie: " + movie);
        this.movieDAO.addMovie(movie);
        System.out.println("movies: " + getAllMovies());
    }

    @Override
    public boolean directorInDB(Director director) {

        for (Director tempDirector : movieDAO.getAllDirectors()) {
            if (tempDirector.getName().equals(director.getName())) {
                if (tempDirector.getSurname().equals(director.getSurname())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Movie> getMoviesByGenre(Movie.Genre genre) {
        return this.movieDAO.getMoviesByGenre(genre);
    }

    @Override
    public List<Movie> findMovies(String pattern) {
        Set<Movie> result = new HashSet<>();
        List<Movie> movies = this.movieDAO.findMovies(pattern);

        result.addAll(movies);

        List<Director> directors = this.movieDAO.findDirectors(pattern);
        for (Director director : directors) {
            List<Movie> moviesForDirector = this.movieDAO.getMoviesByDirectorId(director.getId());

            result.addAll(moviesForDirector);
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Movie> findMoviesByFilter(UserFilter userFilter) {

        Movie.Genre genre = userFilter.getGenre();
        String pattern = userFilter.getLastFindPattern();
        String productionYear = userFilter.getProductionYear();

        if (pattern != null && genre == null && productionYear == null) {
            return findMovies(userFilter.getLastFindPattern());
        }

        if (pattern != null && genre != null && productionYear == null) {
            return this.movieDAO.getMoviesByPatternAndGenre(pattern, genre);
        }

        if (pattern == null && genre != null && productionYear == null) {
            return this.movieDAO.getMoviesByGenre(genre);
        }

        return null;
    }
}
