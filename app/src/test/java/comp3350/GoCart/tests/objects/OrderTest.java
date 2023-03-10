package comp3350.GoCart.tests.objects;

import org.junit.Test;

import comp3350.GoCart.objects.Order;
import static org.junit.Assert.*;

public class OrderTest {


    @Test
    public void testNullOrder() {

        System.out.println("Starting testNullOrder");
        Order order;

        order = new Order(1, 1, 3);
        assertNotNull("Order should not be null", order);

        System.out.println("Finished testNullOrder");
    }


    @Test
    public void testGetters() {
        System.out.println("Starting testGetters");
        Order order;

        order = new Order(1, 1, 3);
        assertTrue("OID should be equal", order.getOrderID() == 1);
        assertTrue("CID should be equal", order.getCustomerID() == 1);
        assertTrue("SID should be equal", order.getStoreID() == 3);

        System.out.println("Finished testGetters");
    }

    @Test
    public void testEquals() {
        System.out.println("Starting testEquals");
        Order order1;
        Order order2;

        order1 = new Order(1, 1, 3);
        order2 = new Order(1, 1, 3);
        assertTrue("Orders should be equal", order1.equals(order2));

        System.out.println("Finished testEquals");
    }

}
