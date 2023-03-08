package comp3350.GoCart.application;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;
<<<<<<< app/src/main/java/comp3350/GoCart/application/Services.java
import comp3350.GoCart.persistence.hsqldb.ProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StorePersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StoreProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.UserPersistence;
>>>>>>> app/src/main/java/comp3350/GoCart/application/Services.java
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.stubs.StoreProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.UserPersistenceStub;

public class Services{
    private static final boolean useHSQLDB = false;


    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;
    private static StoreProductPersistence storeProductPersistence = null;

    private static UserPersistence userPersistence = null;

    public static synchronized StorePersistence getStorePersistence(){
        if (storePersistence == null){
            if(useHSQLDB) {
                storePersistence = new StorePersistenceHSQLDB(Main.getDBPathName());
            } else{
                storePersistence = new StorePersistenceStub();

            }
        }
        return storePersistence;
    }
    public static synchronized ProductPersistence getProductPersistence() {
        if (productPersistence == null) {
            if (storePersistence == null) {
                if (useHSQLDB) {
                    productPersistence = new ProductPersistenceHSQLDB(Main.getDBPathName());

                } else {
                    productPersistence = new ProductPersistenceStub();
                }
            }
        }
            return productPersistence;

    }

<<<<<<< app/src/main/java/comp3350/GoCart/application/Services.java
    public static synchronized StoreProductPersistence getStoreProductPersistence() {
        if (storeProductPersistence == null) {
            if (storePersistence == null) {
                if (useHSQLDB) {
                    storeProductPersistence = new StoreProductPersistenceHSQLDB(Main.getDBPathName());

                }
            } else {
                storeProductPersistence = new StoreProductPersistenceStub();
            }
        }
        return storeProductPersistence;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null){
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
>>>>>>> app/src/main/java/comp3350/GoCart/application/Services.java
    }
}
