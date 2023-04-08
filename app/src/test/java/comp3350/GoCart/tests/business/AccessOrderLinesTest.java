package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessOrderLines;
import comp3350.GoCart.business.AccessOrders;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.OrderLinePersistence;

import static org.mockito.Mockito.*;

public class AccessOrderLinesTest extends TestCase {
    private AccessOrderLines orderLines;
    private OrderLinePersistence orderLinePersistence;

    public AccessOrderLinesTest() {
        super();
        orderLinePersistence = mock(OrderLinePersistence.class);
        orderLines = new AccessOrderLines(orderLinePersistence); //inject the stub
    }


    @Test
    public void testgetSortedItemsItemPresent() {
        System.out.println("Starting testgetSortedItemsItemPresent");
        when(orderLinePersistence.getAllOrderLineItems(99)).thenReturn(new ArrayList<OrderLineItem>());
        //get all the orderLines from order 99 doesnt exist
        List<OrderLineItem> sortedOrderLines = orderLines.getSortedOrderItems(99);

        assertTrue("list size should be 0", sortedOrderLines.size() == 0);

        System.out.println("Finished test getSortedItemsItemPresent");
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

        //make sure the mock returns the right stuff for the, needs to return the inserted item
        when(orderLinePersistence.insertOrderLine(oli1)).thenReturn(oli1);
        when(orderLinePersistence.insertOrderLine(oli2)).thenReturn(oli2);
        when(orderLinePersistence.insertOrderLine(oli3)).thenReturn(oli3);

        //needs to return the order line item
        when(orderLinePersistence.getOrderLineItem(4, 5)).thenReturn(oli1);
        when(orderLinePersistence.getOrderLineItem(4, 24)).thenReturn(oli2);
        when(orderLinePersistence.getOrderLineItem(4, 4)).thenReturn(oli3);

        lineItems.add(oli1);
        lineItems.add(oli2);
        lineItems.add(oli3);

        assertTrue("Should be able to insert", orderLines.insertAllItems(lineItems));

        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 5).equals(new OrderLineItem(order, new Product.ProductBuilder().productID("5").build(), new BigDecimal(5.24))));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 24).equals(new OrderLineItem(order, new Product.ProductBuilder().productID("24").build(), new BigDecimal(9.99))));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 4).equals(new OrderLineItem(order, new Product.ProductBuilder().productID("4").build(), new BigDecimal(20.44))));
    }




    @Test
    public void testInsertOrderLineItemPresent() {
        System.out.println("Starting  testInsertOrderLineItemPresent ");
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
