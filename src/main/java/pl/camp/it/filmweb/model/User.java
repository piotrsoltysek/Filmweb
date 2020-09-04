package pl.camp.it.filmweb.model;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String login;
    private String password;
    @OneToMany// TODO: 2020-09-04 Czy mam tutaj dodać Set<Review> i Set<Rating> ?  Jeśli tak to z jaką relacją?
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany // TODO: 2020-09-04 jw.
    private Set<Review> reviews = new HashSet<>();
    @OneToMany // TODO: 2020-09-04 Czy robić Set<Movie> ?
    private Set<Movie> movies = new HashSet<>();




}
