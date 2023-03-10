package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.stubs.UserPersistenceStub;

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

    public AccessUsers(final UserPersistenceStub userPersistence)
    {
        this();
        this.userPersistence = userPersistence;
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
        System.out.println("userPersistence.getUser(email, password)" + "email and password: " + email + " " + password);
        return userPersistence.getUser(email, password);
    }

    public void updateUser(User theUser)
    {
        userPersistence.updateUser(theUser);
    }
//
//    public void updateUserPhone(User user, int phone)
//    {
//        userPersistence.updateUserPhone(user, phone);
//    }
//
//    public void updateUserEmail(User user, String newEmail)
//    {
//        userPersistence.updateUserEmail(user, newEmail);
//    }
//    public void updateUserPassword(final User user, final String newPassword)
//    {
//        userPersistence.updateUserPassword(user, newPassword);
//    }

    public int getNumUsers()
    {
        return userPersistence.getNumUsers();
    }

    public void deleteUser(User user)
    {
        userPersistence.deleteUser(user);
    }
}
