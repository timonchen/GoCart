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



    public void addUser(User newUser)
    {
        userPersistence.addUser(newUser);
    }

    public User getUser(String email, String password)
    {
        return userPersistence.getUser(email, password);
    }

    public int getNumUsers()
    {
        return userPersistence.getNumUsers();
    }

    public void deleteUser(User user)
    {
        userPersistence.deleteUser(user);
    }
}
