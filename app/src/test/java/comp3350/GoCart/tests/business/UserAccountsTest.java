package comp3350.GoCart.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.stubs.UserPersistenceStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserAccountsTest
{
    private AccessUsers accessUsers;

    @Before
    public void setUp(){
        accessUsers = new AccessUsers(new UserPersistenceStub());
    }

    @Test
    public void testAddAndGetUser()
    {
        System.out.println("\nStarting testAddAndGetUser");

        accessUsers.addUser(new User(1178, "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));
        User getUser = accessUsers.getUser("samsmith11@gmail.com", "smith783");

        assertEquals(1178, getUser.getUserID());
        assertEquals("Samuel", getUser.getFirstName());
        assertEquals("Smith", getUser.getLastName());
        assertEquals("117 Becontree Bay", getUser.getAddress());
        assertEquals("Winnipeg", getUser.getCity());
        assertEquals("MB", getUser.getProvince());
        assertEquals("R2M 0T7", getUser.getZipCode());
        assertEquals(1111111111, getUser.getPhone());
        assertEquals("samsmith11@gmail.com", getUser.getEmail());
        assertEquals("smith783", getUser.getPassword());

        System.out.println("\nFinished testAddAndGetUser");
    }

    @Test
    public void testGetNumUsers()
    {
        System.out.println("\nStarting testGetNumUsers");
        accessUsers.addUser(new User(1178, "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));

        assertEquals(1, accessUsers.getNumUsers());

        accessUsers.addUser(new User(7813, "Joey", "Williams", "209 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111118123, "joeywilliams01@gmail.com", "joey621"));
        assertEquals(2, accessUsers.getNumUsers());

        System.out.println("\nFinished testGetNumUsers");

    }

    @Test
    public void testDeleteUser()
    {
        System.out.println("\nStarting testDeleteUser");

        accessUsers.addUser(new User(1178, "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783"));
        User user = accessUsers.getUser("samsmith11@gmail.com", "smith783");
        accessUsers.deleteUser(user);

        user = accessUsers.getUser("samsmith11@gmail.com", "smith783");
        assertNull(user);

        System.out.println("\nFinished testDeleteUser");
    }
}
