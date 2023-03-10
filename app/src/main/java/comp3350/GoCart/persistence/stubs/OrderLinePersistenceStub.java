package comp3350.GoCart.persistence.stubs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.persistence.OrderLinePersistence;

public class OrderLinePersistenceStub implements OrderLinePersistence {

    private List<OrderLineItem> orderLines;
    public OrderLinePersistenceStub() {
        orderLines = new ArrayList<>();
        //random orderlines
        orderLines.add(new OrderLineItem(1, 5, new BigDecimal(14.34)));
        orderLines.add(new OrderLineItem(1, 24, new BigDecimal(12.34)));
        orderLines.add(new OrderLineItem(1, 4, new BigDecimal(5.44)));
        orderLines.add(new OrderLineItem(2, 5, new BigDecimal(9.99)));
        orderLines.add(new OrderLineItem(2, 7, new BigDecimal(150.55)));
        orderLines.add(new OrderLineItem(3, 8, new BigDecimal(20.05)));
        orderLines.add(new OrderLineItem(3, 5, new BigDecimal(18.99)));
    }

    @Override
    public OrderLineItem insertOrderLine(OrderLineItem toInsert) {
        orderLines.add(toInsert);

        return toInsert;
    }
    @Override
    public OrderLineItem getOrderLineItem(int orderID, int productID) {
        for(OrderLineItem oli : orderLines) {
            if(oli.getOrderID() == orderID && oli.getProductID() == productID) {
                return oli;
            }
        }

        //return impossible value
        return new OrderLineItem(-1, -1, new BigDecimal(-1));
    }
    @Override
    public List<OrderLineItem> getAllOrderLineItems(int orderID) {
        List<OrderLineItem> items = new ArrayList<>();

        for(OrderLineItem oli : orderLines) {
            if(oli.getOrderID() == orderID) {
                items.add(oli);
            }
        }

        return items;
    }
}
