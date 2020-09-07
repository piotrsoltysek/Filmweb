package pl.camp.it.filmweb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    IMovieDAO movieDAO;


    @Override
    public List<Movie> getAllMovies() {
        return this.movieDAO.getAllMovies();
    }

    @Override
    public void addMovie(Movie movie) {
        this.movieDAO.addMovie(movie);
    }

}
