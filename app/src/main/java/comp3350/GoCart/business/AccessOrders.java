package comp3350.GoCart.business;

/*
This is the access Orders class and deals with all logic needed for the orders in our app
IT interacts with the DB through the orderPersistence interface
 */

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Order;
import comp3350.GoCart.persistence.OrderPersistence;

public class AccessOrders {

    private OrderPersistence orderPersistence;


    public AccessOrders() {
        orderPersistence = Services.getOrderPersistence();
    }

    public AccessOrders(final OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    //gets all of the orders for a specific customer
    //sorts all of the orders so that the UI gets a sorted list
    // takes an int paramter for the customer ID as the DB uses integers
    public List<Order> getSortedOrders(int customerID) {
        List<Order> sortedOrders = orderPersistence.getAllOrders(customerID);

        Collections.sort(sortedOrders, (a, b) -> Integer.compare(Integer.parseInt(a.getOrderID()), Integer.parseInt(b.getOrderID())));

        return sortedOrders;
    }

    //get an order from the DB, takes an int paramter for orderID as the DB uses integers
    public Order getOrder(int orderID) {
        return orderPersistence.getOrder(orderID);
    }

    //inserts an order into the DB and takes the order to insert as a param
    public Order insertOrder(Order toInsert) {
        return orderPersistence.insertOrder(toInsert);
    }
}
