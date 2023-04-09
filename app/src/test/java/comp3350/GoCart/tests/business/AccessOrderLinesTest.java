package comp3350.GoCart.tests.business;

import static org.mockito.Mockito.mock;

import junit.framework.TestCase;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessOrderLines;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.OrderLinePersistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessOrderLinesTest extends TestCase {
    private AccessOrderLines orderLines;
    private OrderLinePersistence orderLinePersistence;

    public AccessOrderLinesTest() {
        super();
        orderLinePersistence = mock(OrderLinePersistence.class);
        orderLines = new AccessOrderLines(orderLinePersistence); //inject the stub

        Order order = new Order.OrderBuilder().orderID("4").build();
        //create the order line items with the order we created
        OrderLineItem oli1 = new OrderLineItem(order, new Product.ProductBuilder().productID("5").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order, new Product.ProductBuilder().productID("24").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order, new Product.ProductBuilder().productID("4").build(), new BigDecimal(20.44));

        //need to return what we inserted
        when(orderLinePersistence.insertOrderLine(oli1)).thenReturn(oli1);
        when(orderLinePersistence.insertOrderLine(oli2)).thenReturn(oli2);
        when(orderLinePersistence.insertOrderLine(oli3)).thenReturn(oli3);

        //need to also make sure we return the right stuff from the get
        when(orderLinePersistence.getOrderLineItem(4, 5)).thenReturn(oli1);
        when(orderLinePersistence.getOrderLineItem(4, 24)).thenReturn(oli2);
        when(orderLinePersistence.getOrderLineItem(4, 4)).thenReturn(oli3);
    }


    @Test
    public void testgetSortedItemsItemPresent() {
        System.out.println("Starting testgetSortedItemsItemPresent");
        //get all the orderLines from order 99 doesnt exist
        List<OrderLineItem> sortedOrderLines = orderLines.getSortedOrderItems(99);

        assertTrue("list size should be 0", sortedOrderLines.size() == 0);

        System.out.println("Finished testgetSortedItemsItemPresent");
    }

    @Test
    public void testInsertAllItems() {
        List<OrderLineItem> lineItems = new ArrayList<>();
        //random orderlines from order
        Order order = new Order.OrderBuilder().orderID("4").build();

        //create the order line items with the order we created
        OrderLineItem oli1 = new OrderLineItem(order, new Product.ProductBuilder().productID("5").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order, new Product.ProductBuilder().productID("24").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order, new Product.ProductBuilder().productID("4").build(), new BigDecimal(20.44));

        lineItems.add(oli1);
        lineItems.add(oli2);
        lineItems.add(oli3);

        assertTrue("Should be able to insert", orderLines.insertAllItems(lineItems));

        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 5).equals(oli1));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 24).equals(oli2));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 4).equals(oli3));
    }




    @Test
    public void testInsertOrderLineItemPresent() {
        System.out.println("Starting  testInsertOrderLineItemPresent ");
        Order order = new Order.OrderBuilder().orderID("4").build();

        //create the order line items with the order we created
        OrderLineItem oli1 = new OrderLineItem(order, new Product.ProductBuilder().productID("5").build(), new BigDecimal(5.24));
        OrderLineItem oli2 = new OrderLineItem(order, new Product.ProductBuilder().productID("24").build(), new BigDecimal(9.99));
        OrderLineItem oli3 = new OrderLineItem(order, new Product.ProductBuilder().productID("4").build(), new BigDecimal(20.44));

        OrderLineItem first = orderLines.insertOrderLineItem(oli1);
        OrderLineItem second = orderLines.insertOrderLineItem(oli2);
        OrderLineItem third = orderLines.insertOrderLineItem(oli3);

        assertTrue("Line item should be added and able to get", first.equals(orderLines.getOrderLineItem(Integer.parseInt(first.getOrderID()), Integer.parseInt(first.getProductID()))));
        assertTrue("Line item should be added and able to get", second.equals(orderLines.getOrderLineItem(Integer.parseInt(second.getOrderID()), Integer.parseInt(second.getProductID()))));
        assertTrue("Line item should be added and able to get", third.equals(orderLines.getOrderLineItem(Integer.parseInt(third.getOrderID()), Integer.parseInt(third.getProductID()))));

        System.out.println("Starting  testInsertOrderLineItemPresent ");
    }


    @Test
    public void testInsertOrderLineItemNotPresent() {
        System.out.println("Starting  testInsertOrderLineItemNotPresent ");

        OrderLineItem oli = new OrderLineItem(new Order.OrderBuilder().orderID("-1").build(), new Product.ProductBuilder().productID("-1").build(), new BigDecimal(-1));
        when(orderLinePersistence.getOrderLineItem(8, 99)).thenReturn(oli);

        OrderLineItem test = orderLines.getOrderLineItem(8, 99);
        assertTrue("Should be null", test.equals(new OrderLineItem(new Order.OrderBuilder().orderID("-1").build(), new Product.ProductBuilder().productID("-1").build(), new BigDecimal(-1))));

        System.out.println("Starting  testInsertOrderLineItemNotPresent ");

    }
}
