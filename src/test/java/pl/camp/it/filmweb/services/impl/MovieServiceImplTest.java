package pl.camp.it.filmweb.services.impl;

import org.junit.Before;
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
import pl.camp.it.filmweb.model.Director;
import pl.camp.it.filmweb.services.IMovieService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class MovieServiceImplTest {

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


    @Before
    public void setUpMocks() {
        Mockito.when(this.movieService.findMoviesByPattern(anyString())).thenReturn(new ArrayList<>());

        List<Director> directorsList = new ArrayList<>();

        Director director = new Director();
        director.setId(3);
        director.setName("James");
        director.setSurname("Cameron");

        Director director2 = new Director();
        director2.setId(3);
        director2.setName("James");
        director2.setSurname("Gray");

        directorsList.add(director);
        directorsList.add(director2);

        Mockito.when(this.movieDAO.getDirectorsByPattern(anyString())).thenReturn(directorsList);
        Mockito.when(this.movieDAO.getMoviesByDirectorId(anyInt())).thenReturn(new ArrayList<>());
    }

    @Test
    public void checkMethodCallsDuringFindingMoviesByPattern() {
        String pattern = "james";

        this.movieService.findMoviesByPattern(pattern);

        verify(this.movieDAO, times(2)).getMoviesByPattern(anyString());// TODO: 2020-09-16 Dlaczego 2?
        verify(this.movieDAO, times(1)).getDirectorsByPattern(anyString());
        verify(this.movieDAO, times(2)).getMoviesByDirectorId(anyInt());
    }
}
