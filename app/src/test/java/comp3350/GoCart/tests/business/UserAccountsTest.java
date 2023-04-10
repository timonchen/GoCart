package comp3350.GoCart.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class UserAccountsTest
{
    private AccessUsers accessUsers;
    private UserPersistence userPersistence;

    @Before
    public void setUp(){
        userPersistence = mock(UserPersistence.class);
        accessUsers = new AccessUsers(userPersistence);

    }

    @Test
    public void testAddAndGetUser()
    {
        final User getUser;

        System.out.println("\nStarting testAddAndGetUser");
        final List<User> users = new ArrayList<>();
        users.add(new User("1", "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M0T7", 1111111111, "samsmith11@gmail.com", "smith783"));
        when(userPersistence.getUser("samsmith11@gmail.com", "smith783")).thenReturn(users.get(0));

        getUser = accessUsers.getUser("samsmith11@gmail.com", "smith783");

        assertNotNull(getUser);
        assertTrue("The user ID of the first user should be 1", "1".equals(getUser.getUserID()));
        assertEquals("The first name should match.", "Samuel", getUser.getFirstName());
        assertEquals("The last name should match.", "Smith", getUser.getLastName());
        assertEquals("The address should match.", "117 Becontree Bay", getUser.getAddress());
        assertEquals("The city should match.", "Winnipeg", getUser.getCity());
        assertEquals("The province should match.", "MB", getUser.getProvince());
        assertEquals("The ZIP Code should match", "R2M0T7", getUser.getZipCode());
        assertEquals("The phone should match.", 1111111111, getUser.getPhone());
        assertEquals("The email should match.", "samsmith11@gmail.com", getUser.getEmail());
        assertEquals("The password should match.", "smith783", getUser.getPassword());

        verify(userPersistence).getUser("samsmith11@gmail.com", "smith783");

        System.out.println("\nFinished testAddAndGetUser");
    }

    @Test
    public void testGetNumUsers()
    {
        final int numUsers;

        System.out.println("\nStarting testGetNumUsers");
        final List<User> users = new ArrayList<>();
        users.add(new User("1", "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));
        users.add(new User("2", "Joey", "Williams", "209 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111118123, "joeywilliams01@gmail.com", "joey621"));

        when(userPersistence.getNumUsers()).thenReturn(users.size());

        numUsers = accessUsers.getNumUsers();
        assertEquals("There should be 2 users in the system.", 2, numUsers);

        verify(userPersistence).getNumUsers();

        System.out.println("\nFinished testGetNumUsers");
    }

    @Test
    public void testDeleteUser()
    {
        final User user;

        System.out.println("\nStarting testDeleteUser");
        final List<User> users = new ArrayList<>();
        users.add(new User("1", "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));

        when(userPersistence.getUser("samsmith11@gmail.com", "smith783")).thenReturn(users.get(0));
        user = accessUsers.getUser("samsmith11@gmail.com", "smith783");

        doAnswer(invocation -> {
            users.remove(0);
            return null;
        }).when(userPersistence).deleteUser(user);

        accessUsers.deleteUser(user);

        when(userPersistence.getNumUsers()).thenReturn(users.size());
        int numUsers = accessUsers.getNumUsers();
        assertEquals("The user was deleted.", 0, numUsers);

        verify(userPersistence).deleteUser(user);

        System.out.println("\nFinished testDeleteUser");
    }

    public void testUpdateUser()
    {
        System.out.println("\nStarting testUpdateUser");

        final List<User> users = new ArrayList<>();
        users.add(new User("1", "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));
        final User updateUser = new User("1", "updatedTest1", "updatedUser1", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111112, "samsmith11@gmail.com", "smith783");

        doAnswer(invocation -> {
            users.get(0).updateName("updatedTest1 updatedUser1");
            users.get(0).updatePhone(1111111112);
            return null;
        }).when(userPersistence).updateUser(updateUser);

        accessUsers.updateUser(updateUser);

        when(userPersistence.getUser("samsmith11@gmail.com", "smith783")).thenReturn(users.get(0));
        final User getUpdatedUser = accessUsers.getUser("samsmith11@gmail.com", "smith783");
        assertNotNull(getUpdatedUser);
        assertEquals("First name should be updated", "updatedTest1", getUpdatedUser.getFirstName());
        assertEquals("Last name should be updated", "updatedUser1", getUpdatedUser.getLastName());
        assertEquals("Phone should be updated", 1111111111, getUpdatedUser.getPhone());

        verify(userPersistence).updateUser(updateUser);

        System.out.println("Finished testUpdateUser");
    }
}
