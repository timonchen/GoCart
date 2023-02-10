package comp3350.GoCart.business;

import java.util.ArrayList;
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

    public List<Store> getStores() {
        stores = storePersistence.getAllStores();
        return Collections.unmodifiableList(stores);
    }

    public List<Store> getStoresByName(String storeName) {
        List<Store> storeList = getStores();
        List<Store> storesWanted = new ArrayList<>();

        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).getStoreName().equals(storeName)) {
                storesWanted.add(storeList.get(i));
            }
        }

        return storesWanted;
    }
}
