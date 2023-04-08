package comp3350.GoCart.business;

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
    public List<OrderLineItem> getSortedOrderItems(int orderID) {
        List<OrderLineItem> sortedByPrice = orderLinePersistence.getAllOrderLineItems(orderID);

        Collections.sort(sortedByPrice, (a, b) -> a.getPrice().compareTo(b.getPrice()));

        return sortedByPrice;
    }

    public boolean insertAllItems(List<OrderLineItem> ordersToInsert) {
        for(OrderLineItem oli : ordersToInsert) {
            orderLinePersistence.insertOrderLine(oli);
        }

        return true;
    }

    public OrderLineItem getOrderLineItem(int orderID, int productID) {
        return orderLinePersistence.getOrderLineItem(orderID, productID);
    }

    public OrderLineItem insertOrderLineItem(OrderLineItem toInsert) {
        return orderLinePersistence.insertOrderLine(toInsert);
    }

}
