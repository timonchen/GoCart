package comp3350.GoCart.objects;


import androidx.annotation.NonNull;

public class Store {
    

    private final String storeName;
    private final String storeAddress; // zones

    private double distToUser;

    public Store(final String newStoreName, final String newStoreAddress){
        storeAddress = newStoreAddress;
        storeName = newStoreName;
        distToUser = 0;
    }


    public String getStoreName(){
        return storeName;
    }

    public String getStoreAddress(){
        return storeAddress;
    }

    public boolean equals(Object other) {
        if(other instanceof Store) {
            Store toCheck = (Store)other;

            return storeName.equals(toCheck.storeName) && storeAddress.equals(toCheck.storeAddress);
        }

        return false;
    }

    public String toString() {
        return "Name: " + storeName + "\nAddress: " + storeAddress;
    }

    public void setDistToUser(double dist) {
        distToUser = dist;
    }
    public double getDistToUser() {
        return distToUser;
    }

    public double compareTo(@NonNull Store other) {
        return distToUser - other.distToUser;
    }


}