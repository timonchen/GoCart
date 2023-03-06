package comp3350.GoCart.application;

import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;
import comp3350.GoCart.persistence.hsqldb.ProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StorePersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StoreProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.stubs.StoreProductPersistenceStub;

public class Services{
    private static final boolean useHSQLDB = false;


    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;
    private static StoreProductPersistence storeProductPersistence = null;

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

    }
}
