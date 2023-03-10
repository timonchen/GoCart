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

    public int getNumUsers()
    {
        return users.size();
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

    public void deleteUser(User user)
    {
        users.remove(user);
    }
}
