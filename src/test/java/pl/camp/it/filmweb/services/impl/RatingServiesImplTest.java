package pl.camp.it.filmweb.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.filmweb.AppConfigurationTest;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.dao.IReviewDAO;
import pl.camp.it.filmweb.dao.IUserDAO;
import pl.camp.it.filmweb.model.Rating;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IRatingService;
import pl.camp.it.filmweb.session.SessionObject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class RatingServiesImplTest {

    @Autowired
    IRatingService ratingService;

    @Autowired
    IMovieService movieService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IMovieDAO movieDAO;

    @MockBean
    IReviewDAO reviewDAO;

    @MockBean
    IRatingDAO ratingDAO;

    @MockBean
    SessionObject sessionObject;


    @Test
    public void countingAverage() {
        Rating r1 = new Rating();
        r1.setScore(5);

        Rating r2 = new Rating();
        r2.setScore(10);

        Rating r3 = new Rating();
        r3.setScore(3);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(r1);
        ratings.add(r2);
        ratings.add(r3);

        double expectedAverage = 6.0;

        double result = this.ratingService.getMovieAverageRating(ratings);

        Assert.assertEquals(expectedAverage, result, 0.001);
    }

    @Test
    public void countingAverage0() {
        List<Rating> ratings = new ArrayList<>();
        double expectedAverage = 0.0;

        double result = this.ratingService.getMovieAverageRating(ratings);

        Assert.assertEquals(expectedAverage, result, 0.001);
    }
}