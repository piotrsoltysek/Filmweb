package pl.camp.it.filmweb.services;

import pl.camp.it.filmweb.model.User;

public interface IUserService {
    boolean authenticate(User user);
    boolean registerUser(User user, String repeatedPassword);
    User getUserById(int id);
    User getUserByLogin(String login);
}
