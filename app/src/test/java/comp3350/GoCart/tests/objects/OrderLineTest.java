package comp3350.GoCart.tests.objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;

import comp3350.GoCart.objects.OrderLineItem;

public class OrderLineTest {

    @Test
    public void testNullOrderLine() {

        System.out.println("Starting testNullOrder");
        OrderLineItem orderLine;

        orderLine = new OrderLineItem(1, 1, new BigDecimal(10.12));
        assertNotNull("Order should not be null", orderLine);

        System.out.println("Finished testNullOrder");
    }


    @Test
    public void testGetters() {
        System.out.println("Starting testGetters");
        OrderLineItem orderLine;

        orderLine = new OrderLineItem(1, 1, new BigDecimal(10.12));
        assertTrue("OID should be equal", orderLine.getOrderID() == 1);
        assertTrue("PID should be equal", orderLine.getProductID() == 1);
        assertTrue("SID should be equal", orderLine.getPrice().equals(new BigDecimal(10.12)));

        System.out.println("Finished testGetters");
    }

    @Test
    public void testEquals() {
        System.out.println("Starting testEquals");
        OrderLineItem orderLine1;
        OrderLineItem orderLine2;

        orderLine1 = new OrderLineItem(1, 1, new BigDecimal(10.12));
        orderLine2 = new OrderLineItem(1, 1, new BigDecimal(10.12));
        assertTrue("Orders should be equal", orderLine1.equals(orderLine2));

        System.out.println("Finished testEquals");
    }
}
