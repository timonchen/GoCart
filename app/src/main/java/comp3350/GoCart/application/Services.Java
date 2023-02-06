package comp3350.GoCart.application;

import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;
import comp3350.GoCart.persistence.ProductPersistence;


public class Services{
    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;

    public static synchronized StorePersistence getStorePersistence(){
        if (storePersistence == null){
            storePersistence = new StorePersistenceStub();
        }
        return storePersistence;

    }
    public static synchronized ProductPersistence getProductPersistence(){
        if (productPersistence == null){
            productPersistence = new ProductPersistenceStub();
        }
        return productPersistence;

    }

}
