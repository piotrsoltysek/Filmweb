package pl.camp.it.filmweb.filter;

import pl.camp.it.filmweb.model.Movie;

public class UserFilter {
    private String lastFindPattern;
    private Movie.Genre genre;
    private String productionYear;

    public String getLastFindPattern() {
        return lastFindPattern;
    }

    public void setLastFindPattern(String lastFindPattern) {
        this.lastFindPattern = lastFindPattern;
    }

    public Movie.Genre getGenre() {
        return genre;
    }

    public void setGenre(Movie.Genre genre) {
        this.genre = genre;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "lastFindPattern='" + lastFindPattern + '\'' +
                ", genre=" + genre +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}
