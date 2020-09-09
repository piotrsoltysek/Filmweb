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
        this.movieDAO.addMovie(movie);
    }

    @Override
    public boolean directorInDB(Director director) {

        for (Director tempDirector : movieDAO.getAllDirectors()) {
            if (tempDirector.getName().equals(director.getName())) {
                System.out.println("name the same");
                if (tempDirector.getSurname().equals(director.getSurname())) {
                    System.out.println("surname the same");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Movie> findMoviesByGenre(Movie.Genre genre) {
        return this.movieDAO.getMoviesByGenre(genre);
    }

    @Override
    public List<Movie> findMoviesByPattern(String pattern) {
        Set<Movie> result = new HashSet<>();
        List<Movie> movies = this.movieDAO.getMoviesByPattern(pattern);

        result.addAll(movies);

        List<Director> directors = this.movieDAO.getDirectorsByPattern(pattern);
        for (Director director : directors) {
            List<Movie> moviesForDirector = this.movieDAO.getMoviesByDirectorId(director.getId());

            result.addAll(moviesForDirector);
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Movie> findMoviesByPatternAndGenre(String pattern, Movie.Genre genre) {
        Set<Movie> result = new HashSet<>();

        for (Movie movie : findMoviesByPattern(pattern)) {
            if (movie.getGenre().equals(genre)) {
                result.add(movie);
            }
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Movie> findMoviesByFilter(UserFilter userFilter) {

        Movie.Genre genre = userFilter.getGenre();
        String pattern = userFilter.getLastFindPattern();
        String productionYear = userFilter.getProductionYear();

        //pattern
        if (pattern != null && genre == null && productionYear == null) {
            return findMoviesByPattern(pattern);
        }
        //pattern & genre
        if (pattern != null && genre != null && productionYear == null) {
            return findMoviesByPatternAndGenre(pattern, genre);

        }
        //genre
        if (pattern == null && genre != null && productionYear == null) {
            return findMoviesByGenre(genre);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Movie> findMoviesByUserId(int id) {
        return this.movieDAO.getMoviesByUserId(id);
    }

    @Override
    public Movie findMovieById(int id) {
        return this.movieDAO.getMovieById(id);
    }


}
