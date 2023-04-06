package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.User;

public interface UserPersistence
{

    void addUser(final User newUser);

    User getUser(String email, String password);

    int getNumUsers();

    void updateUser(final User user);

//    void updateUserPhone(final User user, final int phone);
//
//    void updateUserEmail(final User user, final String newEmail);
//
//    void updateUserPassword(final User user, final String newPassword);

    void deleteUser(final User user);
}
