package pl.camp.it.filmweb.model;

import javax.persistence.*;


@Entity(name = "tmovie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String productionYear;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Director director;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    private double average = 0.0;

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", director=" + director +
                ", genre=" + genre +
                ", user=" + user +
                ", average=" + average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie movie = (Movie) o;
            return movie.id == this.id;
        }
        return false;
    }



    @Override
    public int hashCode() {
        return this.id;
    }


    public enum Genre {
        ACTION,
        COMEDY,
        DRAMA,
        FANTASY,
        HORROR,
        ROMANCE,
        THRILLER,
        WESTERN,
        OTHER;
    }
}
