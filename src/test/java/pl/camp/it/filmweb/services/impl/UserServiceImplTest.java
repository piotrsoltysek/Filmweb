package pl.camp.it.filmweb.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
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
import pl.camp.it.filmweb.model.User;
import pl.camp.it.filmweb.services.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigurationTest.class})
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

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
        User user = new User();
        user.setId(5);
        user.setLogin("piotrek");
        user.setPassword(DigestUtils.md5Hex("haslo"));
        Mockito.when(this.userDAO.getUserByLogin("piotrek")).thenReturn(user);

        user = new User();
        user.setId(5);
        user.setLogin("admin");
        user.setPassword(DigestUtils.md5Hex("admin"));
        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(user);

        Mockito.when(userDAO.getUserByLogin("badLogin")).thenReturn(null);
    }


    @Test//test do metody public boolean authenticate(User user)
    public void wrongPasswordAuthenticationTest() {
        User user = new User();
        user.setLogin("piotrek");
        user.setPassword("fewioueruiohfoqwieu");

        boolean result = userService.authenticate(user);
        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);
        Assert.assertTrue(result);
    }

    @Test
    public void wrongLoginAuthenticationTest() {
        User user = new User();
        user.setLogin("badLogin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);
        Assert.assertFalse(result);
    }

    @Test
    public void wrongRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "januszjanusz";

        boolean result = userService.registerUser(user, repeatedPassword);
        Assert.assertFalse(result);
    }

    @Test
    public void correctRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "janusz";

        boolean result = userService.registerUser(user, repeatedPassword);
        Assert.assertTrue(result);
    }
}