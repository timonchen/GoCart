package comp3350.GoCart.business;

/*
This is the access Orders class and deals with all logic needed for the orders in our app
IT interacts with the DB through the orderPersistence interface
 */

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.persistence.OrderLinePersistence;

public class AccessOrderLines {
    private OrderLinePersistence orderLinePersistence;

    public AccessOrderLines() {
        orderLinePersistence = Services.getOrderLinePersistence();
    }

    public AccessOrderLines(final OrderLinePersistence orderLinePersistence) {
        this.orderLinePersistence = orderLinePersistence;
    }


    //gets a sorted list of the orderLineItems for an order
    //this gets everything that was apart of the order
    public List<OrderLineItem> getSortedOrderItems(int orderID) {
        List<OrderLineItem> sortedByPrice = orderLinePersistence.getAllOrderLineItems(orderID);

        Collections.sort(sortedByPrice, (a, b) -> a.getPrice().compareTo(b.getPrice()));

        return sortedByPrice;
    }

    //This inserts a list of orders to the persistence layer
    //The paramter is all of the items for the order to add
    public boolean insertAllItems(List<OrderLineItem> ordersToInsert) {
        for(OrderLineItem oli : ordersToInsert) {
            orderLinePersistence.insertOrderLine(oli);
        }

        return true;
    }

    //Simply gets a specifc order line item
    //the orderID and product ID are given as both are needed to find an orderline item in the persistence layer
    //returns the OLI inserted
    public OrderLineItem getOrderLineItem(int orderID, int productID) {
        return orderLinePersistence.getOrderLineItem(orderID, productID);
    }

    //Simply gets a inserts an order line item
    //the paramter is an order line item which is then pased to the persistence layer method
    //it returns the OLI if the insertion was compelete
    public OrderLineItem insertOrderLineItem(OrderLineItem toInsert) {
        return orderLinePersistence.insertOrderLine(toInsert);
    }

}
