package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;

public class AccessUsers
{
    private UserPersistence userPersistence;

    private List<User> users;

    private User user;

    private static User loggedInUser;

    public AccessUsers()
    {
        userPersistence = Services.getUserPersistence();
        users = null;
    }

    public void setLoggedInUser(User loggedInUser)
    {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser()
    {
        return this.loggedInUser;
    }

    public List<User> getUsers()
    {
        users = userPersistence.getUserSequential();
        return Collections.unmodifiableList(users);
    }

    public User addUser(User newUser)
    {
        return userPersistence.addUser(newUser);
    }

    public User getUser(String email, String password)
    {
        return userPersistence.getUser(email, password);
    }

    public void deleteUser(User user)
    {
        userPersistence.deleteUser(user);
    }
}
