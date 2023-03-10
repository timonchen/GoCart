package comp3350.GoCart.objects;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;

public class Store implements Parcelable {
    

    private final String storeID;
    private final String storeName;
    private final String storeAddress; // zones
    private double distToUser;




    public Store(final String newStoreID , final String newStoreName, final String newStoreAddress){
        storeAddress = newStoreAddress;
        storeName = newStoreName;
        distToUser = 0;
        storeID = newStoreID;
    }

    public Store(final String newStoreID) {
        storeID = newStoreID;
        storeName = null;
        storeAddress = null;
    }

    // The next following three methods are so that this class is parcelable. A parcelable class can be sent between activities.
    protected Store(Parcel in) {
        storeName = in.readString();
        storeAddress = in.readString();
        distToUser = in.readDouble();
        storeID = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeName);
        dest.writeString(storeAddress);
        dest.writeDouble(distToUser);
        dest.writeString(storeID);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    // End of parcel methods

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

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

    @NonNull
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