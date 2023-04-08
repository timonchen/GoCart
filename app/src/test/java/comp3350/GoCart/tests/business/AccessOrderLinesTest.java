package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessOrderLines;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.persistence.stubs.OrderLinePersistenceStub;

public class AccessOrderLinesTest extends TestCase {
    private AccessOrderLines orderLines;

    public AccessOrderLinesTest() {
        super();
        orderLines = new AccessOrderLines(new OrderLinePersistenceStub()); //inject the stub
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
        lineItems.add(new OrderLineItem(4, 5, new BigDecimal(5.24)));
        lineItems.add(new OrderLineItem(4, 24, new BigDecimal(9.99)));
        lineItems.add(new OrderLineItem(4, 4, new BigDecimal(20.44)));

        assertTrue("Should be able to insert", orderLines.insertAllItems(lineItems));

        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 5).equals(new OrderLineItem(4, 5, new BigDecimal(5.24))));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 24).equals(new OrderLineItem(4, 24, new BigDecimal(9.99))));
        assertTrue("Should be able to get the OrderLineItem now", orderLines.getOrderLineItem(4, 4).equals(new OrderLineItem(4, 4, new BigDecimal(20.44))));
    }




    @Test
    public void testInsertOrderLineItemPresent() {
        System.out.println("Starting  testInsertOrderLineItemPresent ");
        OrderLineItem first = orderLines.insertOrderLineItem(new OrderLineItem(4, 5, new BigDecimal(5.24)));
        OrderLineItem second = orderLines.insertOrderLineItem(new OrderLineItem(4, 24, new BigDecimal(5.24)));
        OrderLineItem third = orderLines.insertOrderLineItem(new OrderLineItem(4, 19, new BigDecimal(5.24)));

        assertTrue("Line item should be added and able to get", first.equals(orderLines.getOrderLineItem(first.getOrderID(), first.getProductID())));
        assertTrue("Line item should be added and able to get", second.equals(orderLines.getOrderLineItem(second.getOrderID(), second.getProductID())));
        assertTrue("Line item should be added and able to get", third.equals(orderLines.getOrderLineItem(third.getOrderID(), third.getProductID())));

        System.out.println("Starting  testInsertOrderLineItemPresent ");
    }


    @Test
    public void testInsertOrderLineItemNotPresent() {
        System.out.println("Starting  testInsertOrderLineItemNotPresent ");

        assertTrue("Should be null", orderLines.getOrderLineItem(8, 99).equals(new OrderLineItem(-1, -1, new BigDecimal(-1))));

        System.out.println("Starting  testInsertOrderLineItemNotPresent ");
    }
}
