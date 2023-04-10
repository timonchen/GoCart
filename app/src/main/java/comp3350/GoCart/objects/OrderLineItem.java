package comp3350.GoCart.objects;
/*
* This is the orderline item which is exactly what it sounds like
* Each order has order line items which have an order ID product ID and price paid
* We use Order and Product to represent these fields
*/

import java.math.BigDecimal;

public class OrderLineItem {
    private Order order;
    private Product product;
    private BigDecimal price;

    public OrderLineItem(final Order order, final Product product, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.price = price;
    }

    public String getOrderID() {
        return order.getOrderID();
    }

    public String getProductID() {
        return product.getProductID();
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof OrderLineItem) {
            return order.getOrderID().equals(((OrderLineItem) other).getOrderID()) && getProductID().equals(((OrderLineItem) other).getProductID());
        }

        return false;

    }
}
