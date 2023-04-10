package comp3350.GoCart.tests.objects;

import org.junit.Test;

import comp3350.GoCart.objects.User;
import static org.junit.Assert.*;

public class UserTest
{
    @Test
    public void testUser1()
    {
        User user;
        System.out.println("\nStarting testUser1");

        user = new User("1178", "Samuel", "Smith", "117 Becontree Bay", "Winnipeg", "MB", "R2M 0T7", 1111111111, "samsmith11@gmail.com", "smith783");
        assertTrue("1178".equals(user.getUserID()));
        assertEquals("Samuel", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("117 Becontree Bay", user.getAddress());
        assertEquals("Winnipeg", user.getCity());
        assertEquals("MB", user.getProvince());
        assertEquals("R2M 0T7", user.getZipCode());
        assertEquals(1111111111, user.getPhone());
        assertEquals("samsmith11@gmail.com", user.getEmail());
        assertEquals("smith783", user.getPassword());

        System.out.println("\nFinished testUser1");
    }
}
