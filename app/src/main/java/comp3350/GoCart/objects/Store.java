package comp3350.GoCart.objects;


import android.os.Parcel;
import android.os.Parcelable;



public class Store {
    private String storeID;
    private String storeName;
    private String storeAddress;
    private double distToUser;



    private Store() {}
    public Store(final String newStoreID , final String newStoreName, final String newStoreAddress){
        storeAddress = newStoreAddress;
        storeName = newStoreName;
        distToUser = 0; //initally set to 0
        storeID = newStoreID;
    }

    public Store(final String newStoreID) {
        storeID = newStoreID;
        storeName = null;
        storeAddress = null;
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

    public String getStoreID() {return storeID; }


    public String toString() {
        return "Name: " + storeName + "\nAddress: " + storeAddress;
    }

    public void setDistToUser(double dist) {
        distToUser = dist;
    }
    public double getDistToUser() {
        return distToUser;
    }

    //comparte to method to compre stores distances
    public double compareTo(Store other) {
        return distToUser - other.distToUser;
    }

    //This is the storeBuilder class that is used to build the store
    public static class StoreBuilder {
        private Store store;

        public StoreBuilder() {
            this.store = new Store();
        }

        public StoreBuilder storeID(String storeID) {
            this.store.storeID = storeID;
            return this;
        }

        public StoreBuilder storeName(String storeName) {
            this.store.storeName = storeName;
            return this;
        }

        public StoreBuilder storeAddress(String storeAddress) {
            this.store.storeAddress = storeAddress;
            return this;
        }

        public StoreBuilder distToUser(double distToUser) {
            this.store.distToUser = distToUser;
            return this;
        }

        public Store build() {
            return this.store;
        }
    }
}
