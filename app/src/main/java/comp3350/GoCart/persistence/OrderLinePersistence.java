package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.OrderLineItem;

public interface OrderLinePersistence {

    OrderLineItem insertOrderLine(OrderLineItem toInsert);

    OrderLineItem getOrderLineItem(int orderID, int productID);

    List<OrderLineItem> getAllOrderLineItems(int orderID);
}
