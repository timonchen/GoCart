package comp3350.GoCart.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import comp3350.GoCart.business.AccessOrders;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.OrderPersistence;
import comp3350.GoCart.persistence.hsqldb.OrderPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;


public class AccessOrdersIT {

    private AccessOrders accessOrders;
    private File tempDB;

    @Before
    public void init() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final OrderPersistence persistence = new OrderPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessOrders = new AccessOrders(persistence);

        //insert some orders
        Order order1 = new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("1").build());
        Order order2 = new Order("2", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("2").build());
        Order order3 = new Order("3", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("1").build());
        accessOrders.insertOrder(order1);
        accessOrders.insertOrder(order2);
        accessOrders.insertOrder(order3);
    }

    @Test
    public void insertOrders() {
        System.out.println("Testing inserting orders with DB");
        //insert some orders
        Order order1 = new Order("4", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("1").build());
        Order order2 = new Order("5", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("2").build());
        Order order3 = new Order("6", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("1").build());

        //insert the orders to the DB
        accessOrders.insertOrder(order1);
        accessOrders.insertOrder(order2);
        accessOrders.insertOrder(order3);

        //make sure they were inserted
        assertTrue("Orders should be equal", accessOrders.getOrder(4).equals(order1));
        assertTrue("Orders should be equal", accessOrders.getOrder(5).equals(order2));
        assertTrue("Orders should be equal", accessOrders.getOrder(6).equals(order3));

        System.out.println("Done testing inserting orders with DB");
    }

    @Test
    public void getSortedOrders() {
        System.out.println("Testing get sorted orders with DB");

        List<Order> orders = accessOrders.getSortedOrders(60);

        for(int i = 0; i < orders.size() - 1; i++) {
            assertTrue("Orders should be sorted increasing order", Integer.parseInt(orders.get(i).getOrderID()) < Integer.parseInt(orders.get(i + 1).getOrderID()));
        }

        System.out.println("Done testing get sorted orders with DB");
    }

    @Test
    public void getNonExistentOrder() {
        System.out.println("Testing getting non existent order DB");

        assertTrue("Should return impossible order", accessOrders.getOrder(99).getOrderID().equals("-1"));

        System.out.println("Done testing getting non existent order DB");

    }


    @After
    public void destroy() {
        this.tempDB.delete();
    }
}
