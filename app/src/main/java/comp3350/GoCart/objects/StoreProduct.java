package comp3350.GoCart.objects;


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
    public Store getStore(){ return store;}

    public String getProductID(){ return (product.getProductID()); }
    public String getProductName() { return product.getProductName();}



    public BigDecimal getPrice(){ return price; }


}
