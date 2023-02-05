package comp3350.gocart.objects;

import java.util.objects;


public class Store{
    

    private final String storeName;
    private final String storeAddress; // zones

    public Store(final int newStoreIndex, final String newStoreName, final String newStoreArea){
        storeIndex = newStoreIndex;
        storeName = newStoreName;
        storeArea = newStoreAddress;
    }


    public String getStoreName(){
        return storeName;
    }

    public Bigdecimal getStoreAddress(){
        return storeAddress;
    }


}