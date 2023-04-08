package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

import comp3350.GoCart.business.AccessOrders;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.persistence.stubs.OrderPresistenceStub;

public class AccessOrdersTest extends TestCase {
    private AccessOrders accessOrders;

    public AccessOrdersTest() {
        super();
        accessOrders = new AccessOrders(new OrderPresistenceStub());
    }

    @Test
    public void testGetSortedOrdersCustomerPresent() {
        System.out.println("Starting testGetSortedOrdersCustomerPresent");
        List<Order> sortedByOrder = accessOrders.getSortedOrders(1);

        for(int i = 0; i < sortedByOrder.size()-1; i++) {
            assertTrue("Orders should be sorted", sortedByOrder.get(i).getOrderID() < sortedByOrder.get(i+1).getOrderID());
        }

        System.out.println("Finished testGetSortedOrdersCustomerPresent");

    }

    @Test
    public void testGetSortedOrdersCustomerNotThere() {
        System.out.println("Starting testGetSortedOrdersCustomerNotThere");
        List<Order> sortedByOrder = accessOrders.getSortedOrders(15);


        assertTrue("Orders size should be 0", sortedByOrder.size()==0);


        System.out.println("Finished testGetSortedOrdersCustomerNotThere");

    }

    @Test
    public void testInsertingOrders() {
        System.out.println("Starting testInsertingOrders");

        Order first = accessOrders.insertOrder(new Order(13, 5, 4));
        Order second = accessOrders.insertOrder(new Order(14, 6, 4));
        Order third = accessOrders.insertOrder(new Order(15, 7, 4));

        assertTrue("Order should be added and able to get", first.equals(accessOrders.getOrder(first.getOrderID())));
        assertTrue("Order should be added and able to get", second.equals(accessOrders.getOrder(second.getOrderID())));
        assertTrue("Order should be added and able to get", third.equals(accessOrders.getOrder(third.getOrderID())));


        System.out.println("Finished testInsertingOrders");

    }

    @Test
    public void getOrderNotThere() {
        System.out.println("Starting getOrderNotThere");

        assertTrue("Order should be impossible order values returned", accessOrders.getOrder(99).equals(new Order(-1, -1, -1)));


        System.out.println("Finished getOrderNotThere");

    }

}
