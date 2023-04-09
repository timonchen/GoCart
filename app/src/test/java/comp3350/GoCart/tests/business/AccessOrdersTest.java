package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessOrders;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.OrderPersistence;
import static org.mockito.Mockito.*;

public class AccessOrdersTest extends TestCase {
    private AccessOrders accessOrders;
    private OrderPersistence orderPersistence;

    public AccessOrdersTest() {
        super();
        orderPersistence = mock(OrderPersistence.class);
        accessOrders = new AccessOrders(orderPersistence);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("5", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        orders.add(new Order("2", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        orders.add(new Order("4", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        orders.add(new Order("1", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        orders.add(new Order("3", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));

        when(orderPersistence.getAllOrders(1)).thenReturn(orders);
    }

    @Test
    public void testGetSortedOrdersCustomerPresent() {
        System.out.println("Starting testGetSortedOrdersCustomerPresent");

        List<Order> sortedByOrder = accessOrders.getSortedOrders(1);

        for(int i = 0; i < sortedByOrder.size()-1; i++) {
            assertTrue("Orders should be sorted" , Integer.parseInt(sortedByOrder.get(i).getOrderID()) < Integer.parseInt(sortedByOrder.get(i+1).getOrderID()));
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
        //our stub tests
        Order test1 = new Order("1", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build());
        Order test2 = new Order("2", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build());
        Order test3 = new Order("3", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build());

        //for when insert orders is called
        when(orderPersistence.insertOrder(test1)).thenReturn(test1);
        when(orderPersistence.insertOrder(test2)).thenReturn(test2);
        when(orderPersistence.insertOrder(test3)).thenReturn(test3);

        //for when get Order is called
        when(orderPersistence.getOrder(1)).thenReturn(test1);
        when(orderPersistence.getOrder(2)).thenReturn(test2);
        when(orderPersistence.getOrder(3)).thenReturn(test3);


        Order first = accessOrders.insertOrder(new Order("3", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        Order second = accessOrders.insertOrder(new Order("1", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));
        Order third = accessOrders.insertOrder(new Order("2", new User.UserBuilder().userID(1).build(), new Store.StoreBuilder().storeID("1").build()));

        assertTrue("Order should be added and able to get", first.equals(accessOrders.getOrder(Integer.parseInt(first.getOrderID()))));
        assertTrue("Order should be added and able to get", second.equals(accessOrders.getOrder(Integer.parseInt(second.getOrderID()))));
        assertTrue("Order should be added and able to get", third.equals(accessOrders.getOrder(Integer.parseInt(third.getOrderID()))));


        System.out.println("Finished testInsertingOrders");

    }

    @Test
    public void getOrderNotThere() {
        System.out.println("Starting getOrderNotThere");

        when(accessOrders.getOrder(99)).thenReturn(new Order("-1", new User.UserBuilder().userID(-1).build(), new Store.StoreBuilder().storeID("-1").build()));

        assertEquals("Order should be impossible order values returned", accessOrders.getOrder(99), new Order("-1", new User.UserBuilder().userID(-1).build(), new Store.StoreBuilder().storeID("-1").build()));

        System.out.println("Finished getOrderNotThere");

    }

}
