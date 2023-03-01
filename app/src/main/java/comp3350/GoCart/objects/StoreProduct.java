package comp3350.GoCart.objects;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class StoreProduct {
    private final Store store;
    private final Product product;
    private final BigDecimal price;

    public StoreProduct(final Store store, final Product product, final BigDecimal price){
        this.store = store;
        this.product = product;
        this.price = price;
    }

    public String getStoreId(){ return (store.getStoreID() ); }

    public String getStoreName(){ return (store.getStoreName() ); }

    public String getStoreAddress(){ return (store.getStoreAddress()); }

    public double getStoreDistance(){ return (store.getDistToUser()); }

    public String getProductID(){ return (product.getProductID()); }

    public String getProductName(){ return (product.getProductName()); }

    public BigDecimal getPrice(){ return price; }

    //havent tested yet
    @NonNull
    public String toString(){
        return "StoreProduct: %s %s %s %.2f";
    }

}
