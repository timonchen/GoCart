package comp3350.GoCart.tests.business;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import comp3350.GoCart.business.AccessOrderLines;
import comp3350.GoCart.business.AccessOrders;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.OrderLinePersistence;
import comp3350.GoCart.persistence.OrderPersistence;
import comp3350.GoCart.persistence.hsqldb.OrderLinePersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.OrderPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

public class AccessOrderLinesIT {
    private AccessOrderLines accessOrderLines;
    private AccessOrders accessOrders;
    private File tempDB;

    @Before
    public void init() throws IOException {
        this.tempDB = TestUtils.copyDB();

        final OrderLinePersistence orderLinePersistence = new OrderLinePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        final OrderPersistence orderPersistence = new OrderPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        this.accessOrderLines = new AccessOrderLines(orderLinePersistence);
        this.accessOrders = new AccessOrders(orderPersistence);

        //enter some orders into the DB so we can add order line items
        accessOrders.insertOrder(new Order("1", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("1").build()));
        accessOrders.insertOrder(new Order("2", new User.UserBuilder().userID("1").build(), new Store.StoreBuilder().storeID("2").build()));
    }

    @Test
    public void testInsert() {
        System.out.println("Starting orderLineItem integration test insert item");

        Order order1 = new Order.OrderBuilder().orderID("1").build();
        Order order2 = new Order.OrderBuilder().orderID("2").build();

        //create some orderline items
        OrderLineItem oli1 = new OrderLineItem(order1, new Product.ProductBuilder().productID("4521").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order1, new Product.ProductBuilder().productID("6849").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order2, new Product.ProductBuilder().productID("6917").build(), new BigDecimal(20.44));

        accessOrderLines.insertOrderLineItem(oli1);
        accessOrderLines.insertOrderLineItem(oli2);
        accessOrderLines.insertOrderLineItem(oli3);

        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(1, 4521).equals(oli1));
        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(1, 6849).equals(oli2));
        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(2, 6917).equals(oli3));

        System.out.println("Finished orderLineItem integration test insert item");
    }

    @Test
    public void testInsertAll() {
        System.out.println("Starting orderLineItem integration test insert all items");

        Order order1 = new Order.OrderBuilder().orderID("1").build();
        Order order2 = new Order.OrderBuilder().orderID("2").build();

        //create some orderline items
        OrderLineItem oli1 = new OrderLineItem(order1, new Product.ProductBuilder().productID("4521").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order1, new Product.ProductBuilder().productID("6849").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order2, new Product.ProductBuilder().productID("6917").build(), new BigDecimal(20.44));

        List<OrderLineItem> items = new ArrayList<>(Arrays.asList(oli1, oli2, oli3));

        assertTrue("should return true for insert", accessOrderLines.insertAllItems(items));

        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(1, 4521).equals(oli1));
        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(1, 6849).equals(oli2));
        assertTrue("should be able to get the orderLineItem", accessOrderLines.getOrderLineItem(2, 6917).equals(oli3));

        System.out.println("Finished orderLineItem integration test insert all items");
    }

    @Test
    public void getSortedOrderLineItems() {
        System.out.println("Starting get sorted orderLineItem integration test insert all items");

        Order order1 = new Order.OrderBuilder().orderID("1").build();

        //create some orderline items
        OrderLineItem oli1 = new OrderLineItem(order1, new Product.ProductBuilder().productID("4521").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order1, new Product.ProductBuilder().productID("6849").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order1, new Product.ProductBuilder().productID("6917").build(), new BigDecimal(20.44));

        List<OrderLineItem> items = new ArrayList<>(Arrays.asList(oli1, oli2, oli3));

        assertTrue("should return true for insert", accessOrderLines.insertAllItems(items));

        List<OrderLineItem> sorted = accessOrderLines.getSortedOrderItems(1);

        for(int i = 0; i < sorted.size() - 1; i++) {
            assertTrue("price should be sorted", sorted.get(i).getPrice().compareTo(sorted.get(i + 1).getPrice()) <= 0);
        }

        System.out.println("Finished get sorted orderLineItem integration test insert all items");
    }

    @After
    public void destroy() {
        this.tempDB.delete();
    }

}
