package pl.camp.it.filmweb.session;

import pl.camp.it.filmweb.filter.UserFilter;
import pl.camp.it.filmweb.model.User;


public class SessionObject {
    private User user;
    private String lastAddress;
    private UserFilter userFilter;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public UserFilter getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(UserFilter userFilter) {
        this.userFilter = userFilter;
    }
}
