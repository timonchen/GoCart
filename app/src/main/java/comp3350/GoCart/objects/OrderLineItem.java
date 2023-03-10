package comp3350.GoCart.objects;

import java.math.BigDecimal;

public class OrderLineItem {
    private int orderID;
    private int productID;
    private BigDecimal price;

    public OrderLineItem(int orderID, int productID, BigDecimal price) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof OrderLineItem) {
            return orderID == ((OrderLineItem) other).getOrderID() && productID == ((OrderLineItem) other).getProductID();
        }

        return false;

    }
}
