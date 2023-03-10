package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.User;

public interface UserPersistence
{
    User addUser(final User newUser);

    List<User> getUserSequential();

    User getUser(String email, String password);

    void deleteUser(final User user);
}
