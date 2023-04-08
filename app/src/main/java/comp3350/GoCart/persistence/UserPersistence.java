package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.User;

public interface UserPersistence
{

    void addUser(final User newUser);

    User getUser(String email, String password);

    int getNumUsers();

    void updateUser(final User user);

    void deleteUser(final User user);
}
