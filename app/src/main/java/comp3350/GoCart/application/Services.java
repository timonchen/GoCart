package comp3350.GoCart.application;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.stubs.UserPersistenceStub;


public class Services{
    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;

    private static UserPersistence userPersistence = null;

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

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null){
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }
}
