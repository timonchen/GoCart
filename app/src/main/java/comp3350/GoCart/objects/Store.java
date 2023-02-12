package comp3350.GoCart.objects;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;

public class Store {
    

    private final String storeName;
    private final String storeAddress; // zones

    private double distToUser;


    private final ProductPersistenceStub productStub;

    public Store(final String newStoreName, final String newStoreAddress){
        storeAddress = newStoreAddress;
        storeName = newStoreName;
        distToUser = 0;
        productStub = new ProductPersistenceStub();

    }

    public ProductPersistenceStub getProductsStubForTesting(){
        return productStub;
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

    public List<Product> getStoreProducts(){
        return productStub.getProductsStubForTesting();
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