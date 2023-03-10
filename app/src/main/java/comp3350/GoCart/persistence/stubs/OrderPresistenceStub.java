package comp3350.GoCart.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.persistence.OrderPersistence;

public class OrderPresistenceStub implements OrderPersistence {

    List<Order> orders;

    public OrderPresistenceStub() {
        orders = new ArrayList<>();
        //some random orders
        orders.add(new Order(1, 1, 1));
        orders.add(new Order(4, 5, 2));
        orders.add(new Order(2, 3, 4));
        orders.add(new Order(9, 1, 5));
        orders.add(new Order(3, 1, 5));
        orders.add(new Order(5, 4, 5));
        orders.add(new Order(6, 6, 3));
        orders.add(new Order(7, 1, 6));
        orders.add(new Order(8, 5, 1));

    }

    @Override
    public Order insertOrder(Order toInsert) {
        orders.add(toInsert);
        return toInsert;
    }
    @Override
    public Order getOrder(int orderID) {
        for(Order order : orders) {
            if(order.getOrderID() == orderID) {
                return order;
            }
        }
        return new Order(-1, -1, -1);
    }
    @Override
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
