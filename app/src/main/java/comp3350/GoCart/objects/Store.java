package comp3350.GoCart.objects;


public class Store {
    

    private final String storeName;
    private final String storeAddress; // zones


    public Store(final String newStoreName, final String newStoreAddress){
        storeAddress = newStoreAddress;
        storeName = newStoreName;
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


}