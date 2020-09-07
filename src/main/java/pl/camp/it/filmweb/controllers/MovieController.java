package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.User;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class MovieController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IMovieService movieService;

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String showAddMovie(Model model) {
        model.addAttribute("isLogged", (sessionObject.getUser() != null));
        return "addMovie";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@RequestParam String title, @RequestParam String productionYear,
                           @RequestParam String directorName, @RequestParam String directorSurname,
                           @RequestParam String genre) {// TODO: 2020-09-07 zmiana na @ModelAttribiute
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setProductionYear(productionYear);
        Director director = new Director();
        director.setName(directorName);
        director.setSurname(directorSurname);
        movie.setDirector(director);
        Movie.Genre genre1 = Movie.Genre.valueOf(genre);
        movie.setGenre(genre1);
        movie.setUser(sessionObject.getUser());
        this.movieService.addMovie(movie);

        return "redirect:/main";
    }
}



