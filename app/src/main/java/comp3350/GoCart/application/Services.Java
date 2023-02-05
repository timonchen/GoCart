package comp3350.gocart.application;

import comp3350.GoCart.StorePersistence;
import comp3350.GoCart.ProductPersistence;
import comp3350.GoCart.StorePersistenceStub;
import comp3350.GoCart.ProductPersistenceStub;

Public class Services{
    private static StorePersistence StorePersistence = null;
    private static ProductPersistence ProductPersistence = null;

    public static synchronized StorePersistence getStorePersistence(){
        if (storePersistence == null){
            storePersistence = new StorePersistenceStub();
        }
        return storePersistence;

    }
    public static synchronized ProductPersistence getProductPersistence(){
        if (productPersistence == null){
            productPersistence = new productPersistenceStub();
        }
        return productPersistence;

    }

}
