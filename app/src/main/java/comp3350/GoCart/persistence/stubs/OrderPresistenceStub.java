package comp3350.GoCart.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.persistence.OrderPersistence;

public class OrderPresistenceStub implements OrderPersistence {

    List<Order> orders;

    public OrderPresistenceStub() {
        orders = new ArrayList<>();
    }

    public Order insertOrder(Order toInsert) {
        orders.add(toInsert);
        return toInsert;
    }

    public Order getOrder(int orderID) {
        for(Order order : orders) {
            if(order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAllOrders(int customerID) {
        List<Order> matchingOrders = new ArrayList<>();

        for(Order order : orders) {
            if(order.getCustomerID() == customerID) {
                matchingOrders.add(order);
            }
        }

        return matchingOrders;
    }

}
