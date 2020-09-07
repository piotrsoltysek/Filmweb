package pl.camp.it.filmweb.dao;

import pl.camp.it.filmweb.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void addUser(User user);
    User getUserById(int id);
}
