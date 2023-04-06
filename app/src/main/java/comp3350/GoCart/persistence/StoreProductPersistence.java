package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

public interface StoreProductPersistence {
    List<StoreProduct> getStoreProducts(final String storeID);
    List<StoreProduct> getAllStoreProducts();
}