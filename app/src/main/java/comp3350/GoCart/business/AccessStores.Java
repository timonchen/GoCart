package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.StorePersistence;

public class AccessStores{

    
    private StorePersistence storePersistence;
    private List<Store> stores;
    private Store store;
    private int currentStore;


    public AccessStores(){
        storePersistence = Services.getStorePersistence();
        stores = null;
        store = null;
        currentStore = 0;
    }
}
