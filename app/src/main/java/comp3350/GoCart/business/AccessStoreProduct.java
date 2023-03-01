package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;


import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StoreProductPersistence;

public class AccessStoreProduct {


    private StoreProductPersistence storeProductPersistence;
    private List<StoreProduct> storeProducts;



    public AccessStoreProduct(){
        storeProductPersistence = Services.getStoreProductPersistence();
        storeProducts = null;
    }

    public List<StoreProduct> getStoresProducts(String StoreID) {
        storeProducts= storeProductPersistence.getStoreProducts(StoreID);
        return Collections.unmodifiableList(storeProducts);
    }


}
