package comp3350.GoCart.application;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.OrderLinePersistence;
import comp3350.GoCart.persistence.OrderPersistence;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;
import comp3350.GoCart.persistence.hsqldb.OrderLinePersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.OrderPersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.ProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StorePersistenceHSQLDB;
import comp3350.GoCart.persistence.hsqldb.StoreProductPersistenceHSQLDB;
import comp3350.GoCart.persistence.UserPersistence;
import comp3350.GoCart.persistence.hsqldb.UserPersistenceHSQLDB;

import comp3350.GoCart.persistence.ProductPersistence;


public class Services{

    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;
    private static StoreProductPersistence storeProductPersistence = null;
    private static UserPersistence userPersistence = null;
    private static OrderPersistence orderPersistence = null;
    private static OrderLinePersistence orderLinePersistence = null;

    public static synchronized StorePersistence getStorePersistence(){
        if (storePersistence == null){
            storePersistence = new StorePersistenceHSQLDB(Main.getDBPathName());
        }
        return storePersistence;
    }
    public static synchronized ProductPersistence getProductPersistence() {
        if (productPersistence == null) {
            productPersistence = new ProductPersistenceHSQLDB(Main.getDBPathName());
        }
            return productPersistence;

    }

    public static synchronized StoreProductPersistence getStoreProductPersistence() {
        if (storeProductPersistence == null) {
            storeProductPersistence = new StoreProductPersistenceHSQLDB(Main.getDBPathName());

        }
        return storeProductPersistence;
    }

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
        }
        return userPersistence;
    }

    public static synchronized OrderPersistence getOrderPersistence() {
        if(orderPersistence == null) {
            orderPersistence = new OrderPersistenceHSQLDB(Main.getDBPathName());
        }

        return orderPersistence;
    }

    public static synchronized OrderLinePersistence getOrderLinePersistence() {
        if(orderLinePersistence == null) {
            orderLinePersistence = new OrderLinePersistenceHSQLDB(Main.getDBPathName());
        }

        return orderLinePersistence;
    }
}
