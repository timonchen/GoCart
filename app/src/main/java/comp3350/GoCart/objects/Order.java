package comp3350.GoCart.objects;

public class Order {

    private int orderID;
    private int customerID;
    private int storeID;

    public Order(int oID, int cID, int sID) {
        orderID = oID;
        customerID = cID;
        storeID = sID;
    }

    public int getOrderID(){
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getStoreID() {
        return storeID;
    }

    public boolean equals(Object otherOrder) {
        if(otherOrder instanceof Order) {
            return orderID == ((Order) otherOrder).getOrderID();
        }

        return false;
    }
}
