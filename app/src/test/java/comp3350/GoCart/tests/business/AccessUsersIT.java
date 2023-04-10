package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

public class AccessUsersIT
{
    private AccessUsers accessUsers;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final UserPersistence persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUsers = new AccessUsers(persistence);
    }

    @Test
    public void testGetUser()
    {
        final User getUser;

        System.out.println("\nStarting testGetUser");
        getUser = accessUsers.getUser("testuser1@gmail.com", "testuser1");

        assertNotNull(getUser);
        assertTrue("The user ID of the first user should be 1", "1".equals(getUser.getUserID()));
        assertEquals("First name should match.", "test1", getUser.getFirstName());
        assertEquals("The last name should match.", "user1", getUser.getLastName());
        assertEquals("The address should match.", "address1", getUser.getAddress());
        assertEquals("The city should match.", "test city1", getUser.getCity());
        assertEquals("The province should match.", "test province1", getUser.getProvince());
        assertEquals("The ZIP Code should match", "Q1OTH5", getUser.getZipCode());
        assertEquals("The phone should match.", 1111111111, getUser.getPhone());
        assertEquals("The email should match.", "testuser1@gmail.com", getUser.getEmail());
        assertEquals("The password should match.", "testuser1", getUser.getPassword());

        System.out.println("\nFinished testGetUser");
    }

    @Test
    public void testAddUser()
    {
        final User getUser;

        System.out.println("\nStarting testAddUser");
        accessUsers.addUser( "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M0T7", "1111111111", "samsmith11@gmail.com", "smith783", "smith783");

        assertEquals("The number of users should be increased by 1.", 3, accessUsers.getNumUsers());

        getUser = accessUsers.getUser("samsmith11@gmail.com", "smith783");
        assertNotNull(getUser);
        assertTrue("The user ID of the first user should be 3", "3".equals(getUser.getUserID()));
        assertEquals("The first name should match.", "Samuel", getUser.getFirstName());
        assertEquals("The last name should match.", "Smith", getUser.getLastName());
        assertEquals("The address should match.", "117 Becontree Bay", getUser.getAddress());
        assertEquals("The city should match.", "Winnipeg", getUser.getCity());
        assertEquals("The province should match.", "MB", getUser.getProvince());
        assertEquals("The ZIP Code should match", "R2M0T7", getUser.getZipCode());
        assertEquals("The phone should match.", 1111111111, getUser.getPhone());
        assertEquals("The email should match.", "samsmith11@gmail.com", getUser.getEmail());
        assertEquals("The password should match.", "smith783", getUser.getPassword());

        System.out.println("\nFinished testAddUser");
    }

    @Test
    public void testDeleteUser()
    {
        System.out.println("\nStarting testDeleteUser");

        final User user = accessUsers.getUser("testuser1@gmail.com", "testuser1");
        assertEquals("The number of total users before deleting should be 2.", 2, accessUsers.getNumUsers());

        accessUsers.deleteUser(user);
        assertEquals("After deleting a user, there should be 1 less user than before", 1, accessUsers.getNumUsers());

        System.out.println("\nFinished testDeleteUser");
    }

    @Test
    public void testUpdateUser()
    {
        System.out.println("\nStarting testUpdateUser");

        final User user = accessUsers.getUser("testuser1@gmail.com", "testuser1");
        final User updatedUser = new User(user.getUserID(), "updatedTest1", "updatedUser1", user.getAddress(), user.getCity(), user.getProvince(), user.getZipCode(), 1111111111, user.getEmail(), user.getPassword());
        accessUsers.updateUser(updatedUser);

        final User getUpdatedUser = accessUsers.getUser("testuser1@gmail.com", "testuser1");
        assertNotNull(getUpdatedUser);
        assertEquals("First name should be updated", "updatedTest1", getUpdatedUser.getFirstName());
        assertEquals("Last name should be updated", "updatedUser1", getUpdatedUser.getLastName());
        assertEquals("Phone should be updated", 1111111111, getUpdatedUser.getPhone());

        System.out.println("\nFinished testUpdateUser");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
