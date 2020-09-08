package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.session.SessionObject;
import java.util.List;


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
}
