package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.services.IMovieService;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
public class RestController {

    @Autowired
    IMovieService movieService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return this.movieService.getAllMovies();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) {
        return this.movieService.findMovieById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity updateMovieTitle(@RequestBody Movie movie, @PathVariable int id) {
        this.movieService.updateMovieTitle(movie);
        ResponseEntity responseEntity = new ResponseEntity(this.movieService.findMovieById(id), new HttpHeaders(), HttpStatus.OK);
        return responseEntity;
    }
}