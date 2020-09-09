package pl.camp.it.filmweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.User;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IUserService;
import pl.camp.it.filmweb.session.SessionObject;
import javax.annotation.Resource;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IMovieService movieService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2) {
        boolean registerResult = this.userService.registerUser(user, password2);

        if (registerResult) {
            return "redirect:/main";
        } else  {
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setUser(null);

        return "redirect:/main";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String myMovies(Model model) {

        model.addAttribute("isLogged", (sessionObject.getUser() != null));

        List<Movie> movies = this.movieService.findMoviesByUserId(sessionObject.getUser().getId());
        model.addAttribute("movies", movies);
        this.sessionObject.setLastAddress("/user");
        return "main";
    }
}