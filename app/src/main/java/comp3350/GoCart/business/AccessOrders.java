package comp3350.GoCart.business;

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

    public List<Order> getSortedOrders(int customerID) {
        List<Order> sortedOrders = orderPersistence.getAllOrders(customerID);

        Collections.sort(sortedOrders, (a, b) -> Integer.compare(a.getOrderID(), b.getOrderID()));

        return sortedOrders;
    }

    public Order getOrder(int orderID) {
        return orderPersistence.getOrder(orderID);
    }

    public Order insertOrder(Order toInsert) {
        return orderPersistence.insertOrder(toInsert);
    }
}
