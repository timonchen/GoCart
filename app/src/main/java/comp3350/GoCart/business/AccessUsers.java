package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;


public class AccessUsers
{
    private UserPersistence userPersistence;

    public AccessUsers()
    {
        userPersistence = Services.getUserPersistence();
    }

    public AccessUsers(final UserPersistence userPersistence)
    {
        this();
        this.userPersistence = userPersistence;
    }

    public User addUser(String firstName, String lastName, String address, String city, String province, String zipCode, String phoneString, String email, String password, String confirmPassword)
    {
        User newUser = null;
        boolean valid = true;
        int phone = 0;

        if (firstName.isEmpty())
        {
            valid = false;
        }

        if (lastName.isEmpty())
        {
            valid = false;
        }

        if (address.isEmpty())
        {
            valid = false;
        }

        if (city.isEmpty())
        {
            valid = false;
        }

        if (province.isEmpty())
        {
            valid = false;
        }

        if (zipCode.isEmpty())
        {
            valid = false;
        }
        else {
            if (zipCode.length() != 6)
            {
                valid = false;
            }
        }

        try {
            phone = Integer.parseInt(phoneString);

            if (phoneString.length() != 10)
            {
                valid = false;
            }
        }
        catch (NumberFormatException e)
        {
            valid = false;
        }

        if (email.isEmpty())
        {
            valid = false;
        }
        else {
            if (!email.contains("@") || !email.contains("."))
            {
                valid = false;
            }
        }

        if (password.isEmpty())
        {
            valid = false;
        }
        else {
            if (password.length() < 6)
            {
                valid = false;
            }
        }

        if (confirmPassword.isEmpty())
        {
            valid = false;
        }
        else {
            if (!confirmPassword.equals(password))
            {
                valid = false;
            }
        }

        if (valid)
        {
            newUser = new User("0", firstName, lastName, address, city, province, zipCode, phone, email, password);
            userPersistence.addUser(newUser);
        }

        return newUser;
    }

    public User getUser(String email, String password)
    {
        return userPersistence.getUser(email, password);
    }

    public int getNumUsers()
    {
        return userPersistence.getNumUsers();
    }


    public void updateUser(User theUser)
    {
        userPersistence.updateUser(theUser);
    }

    public void deleteUser(User user)
    {
        userPersistence.deleteUser(user);
    }
}
