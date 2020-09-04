package pl.camp.it.filmweb.model;

import javax.persistence.ManyToOne;

public class Review {
    private int id;
    private String review; // TODO: 2020-09-04 Nazwa pola taka sama jak klasy?
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private User user;

}
