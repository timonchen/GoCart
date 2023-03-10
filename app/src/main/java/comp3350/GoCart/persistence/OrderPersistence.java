package comp3350.GoCart.persistence;


import java.util.List;

import comp3350.GoCart.objects.Order;

public interface OrderPersistence {

    Order insertOrder(Order toInsert);

    Order getOrder(int orderID);

    List<Order> getAllOrders(int customerID);
}
