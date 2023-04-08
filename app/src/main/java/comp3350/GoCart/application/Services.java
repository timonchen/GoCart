package comp3350.GoCart.application;

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
    private static String dbName="database";

    private static StorePersistence storePersistence = null;
    private static ProductPersistence productPersistence = null;
    private static StoreProductPersistence storeProductPersistence = null;
    private static UserPersistence userPersistence = null;
    private static OrderPersistence orderPersistence = null;
    private static OrderLinePersistence orderLinePersistence = null;

    public static synchronized StorePersistence getStorePersistence(){
        if (storePersistence == null){
            storePersistence = new StorePersistenceHSQLDB(getDBPathName());
        }
        return storePersistence;
    }
    public static synchronized ProductPersistence getProductPersistence() {
        if (productPersistence == null) {
            productPersistence = new ProductPersistenceHSQLDB(getDBPathName());
        }
            return productPersistence;

    }

    public static synchronized StoreProductPersistence getStoreProductPersistence() {
        if (storeProductPersistence == null) {
            storeProductPersistence = new StoreProductPersistenceHSQLDB(getDBPathName());

        }
        return storeProductPersistence;
    }

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
        }
        return userPersistence;
    }

    public static synchronized OrderPersistence getOrderPersistence() {
        if(orderPersistence == null) {
            orderPersistence = new OrderPersistenceHSQLDB(getDBPathName());
        }

        return orderPersistence;
    }

    public static synchronized OrderLinePersistence getOrderLinePersistence() {
        if(orderLinePersistence == null) {
            orderLinePersistence = new OrderLinePersistenceHSQLDB(getDBPathName());
        }

        return orderLinePersistence;
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }
}
