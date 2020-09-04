package pl.camp.it.filmweb.model;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class Movie {
    private int id;
    private String title;
    private String productionYear;// TODO: 2020-09-04 rok produkcji jako String?
    @ManyToOne
    private Director director;
    @OneToMany // TODO: 2020-09-04 Jaka relacja? Ma być Set? Ma być takie pole w tej klasie?
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany // TODO: 2020-09-04 jw.
    private Set<Review> reviews = new HashSet<>();
    private Category category;
    @ManyToOne
    private User user;












    public enum Category {
        ACTION,
        COMEDY,
        DRAMA,
        FANTASY,
        HORROR,
        ROMANCE,
        THRILLER,
        WESTERN
    }
}
