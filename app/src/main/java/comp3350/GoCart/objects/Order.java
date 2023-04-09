package comp3350.GoCart.objects;

public class Order {

    private String orderID;
    private User user;
    private Store store;

    private Order() {}
    public Order(final String oID, final User user, final Store store) {
        orderID = oID;
        this.user = user;
        this.store = store;
    }

    public String getOrderID(){
        return orderID;
    }

    public int getCustomerID() {
        return user.getUserID();
    }

    public String getStoreID() {
        return store.getStoreID();
    }

    public boolean equals(Object otherOrder) {
        if(otherOrder instanceof Order) {
            return orderID.equals(((Order) otherOrder).getOrderID());
        }

        return false;
    }

    public static class OrderBuilder {
        private Order order;

        public OrderBuilder() {
            this.order = new Order();
        }

        public OrderBuilder orderID(String orderID) {
            this.order.orderID = orderID;
            return this;
        }

        public OrderBuilder user(User user) {
            this.order.user = user;
            return this;
        }

        public OrderBuilder store(Store store) {
            this.order.store = store;
            return this;
        }

        public Order build() {
            return this.order;
        }

    }
}
