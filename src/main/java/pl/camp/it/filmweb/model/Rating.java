package pl.camp.it.filmweb.model;

import javax.persistence.ManyToOne;

public class Rating {
    private int id;
    private int score;
    @ManyToOne // TODO: 2020-09-04 Jaka relacja? Czy ma być w ogóle? Czy ma być Set<Movie>?
    private Movie movie;
    @ManyToOne // TODO: 2020-09-04 Jaka relacja?
    private User user;
}
