package comp3350.GoCart.tests.objects;

import org.junit.Test;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;

import static org.junit.Assert.*;

public class OrderTest {


    @Test
    public void testNullOrder() {

        System.out.println("Starting testNullOrder");
        Order order;

        order = new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("3").build());
        assertNotNull("Order should not be null", order);

        System.out.println("Finished testNullOrder");
    }


    @Test
    public void testGetters() {
        System.out.println("Starting testGetters");
        Order order;

        order = new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("3").build());
        assertTrue("OID should be equal", order.getOrderID().equals("1"));
        assertTrue("CID should be equal", order.getCustomerID().equals("1"));
        assertEquals("SID should be equal", order.getStoreID(), "3");

        System.out.println("Finished testGetters");
    }

    @Test
    public void testEquals() {
        System.out.println("Starting testEquals");
        Order order1;
        Order order2;

        order1 = new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("3").build());
        order2 = new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("3").build());
        assertTrue("Orders should be equal", order1.equals(order2));

        System.out.println("Finished testEquals");
    }

}
