package comp3350.GoCart.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;

public class UserPersistenceStub implements UserPersistence
{
    private List<User> users;

    public UserPersistenceStub()
    {
        users = new ArrayList<>();
    }

    public void addUser(User newUser)
    {
        users.add(newUser);
    }

    public User getUser(String email, String password)
    {
        User user = null;

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).verifyUser(email, password))
            {
                user = users.get(i);
            }
        }

        return user;
    }

    public int getNumUsers()
    {
        return users.size();
    }

    public void updateUser(User user)
    {
        int index;

        index = users.indexOf(user);
        if (index >= 0)
        {
            users.set(index, user);
        }
    }

//    public void updateUserName(User user, String newName)
//    {
//        user.updateName(newName);
//    }
//
//    public void updateUserPhone(User user, int phone)
//    {
//        user.updatePhone(phone);
//    }
//
//    public void updateUserEmail(User user, String newEmail)
//    {
//        user.updateEmail(newEmail);
//    }
//
//    public void updateUserPassword(User user, String newPassword)
//    {
//        user.updatePassword(newPassword);
//    }


    public void deleteUser(User user)
    {
        int index;

        index = users.indexOf(user);

        if (index >= 0)
        {
            users.remove(index);
        }
    }
}
