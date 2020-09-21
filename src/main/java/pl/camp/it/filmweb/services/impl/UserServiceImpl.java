package pl.camp.it.filmweb.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.filmweb.dao.IUserDAO;
import pl.camp.it.filmweb.model.User;
import pl.camp.it.filmweb.services.IUserService;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;


    @Override
    public boolean authenticate(User user) {
        User userFromDataBase = this.userDAO.getUserByLogin(user.getLogin());

        if (userFromDataBase == null) {
            return false;
        }

        if (DigestUtils.md5Hex(user.getPassword()).equals(userFromDataBase.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean registerUser(User user, String repeatedPassword) {
        if (!user.getPassword().equals(repeatedPassword)) {
            return false;
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        this.userDAO.addUser(user);
        return true;
    }

    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    public User getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }


}
