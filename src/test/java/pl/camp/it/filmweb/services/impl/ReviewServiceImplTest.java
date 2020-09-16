package pl.camp.it.filmweb.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.filmweb.AppConfigurationTest;
import pl.camp.it.filmweb.dao.IMovieDAO;
import pl.camp.it.filmweb.dao.IRatingDAO;
import pl.camp.it.filmweb.dao.IReviewDAO;
import pl.camp.it.filmweb.dao.IUserDAO;
import pl.camp.it.filmweb.model.Movie;
import pl.camp.it.filmweb.model.Review;
import pl.camp.it.filmweb.model.User;
import pl.camp.it.filmweb.services.IMovieService;
import pl.camp.it.filmweb.services.IReviewService;
import pl.camp.it.filmweb.session.SessionObject;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class ReviewServiceImplTest {

    @Autowired
    IReviewService reviewService;

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
    public void noUserReviewInDb() {
        User user = new User();
        Movie movie = new Movie();

        boolean result = reviewService.alreadyReviewed(user, movie);
        Assert.assertFalse(result);
    }

    @Test
    public void userReviewInDb() {
        User user = new User();
        Movie movie = new Movie();
        Review review = new Review();

        review.setMovie(movie);
        review.setUser(user);
        reviewDAO.addReview(review);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        Mockito.when(reviewDAO.getUserReview(anyInt(), anyInt())).thenReturn(reviews);

        boolean result = reviewService.alreadyReviewed(user, movie);
        Assert.assertTrue(result);
    }

    @Test
    public void userReviewInDbForAnotherMovie() {
        User user = new User();
        Movie movieToReview = new Movie();
        Movie movieReviewed = new Movie();
        Review review = new Review();

        review.setMovie(movieReviewed);
        review.setUser(user);
        reviewDAO.addReview(review);
        List<Review> reviews = new ArrayList<>();

        Mockito.when(reviewDAO.getUserReview(anyInt(), anyInt())).thenReturn(reviews);

        boolean result = reviewService.alreadyReviewed(user, movieToReview);
        Assert.assertFalse(result);
    }
}
