package comp3350.GoCart.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.objects.Store;


public class StorePersistenceStub implements StorePersistence {

    private List<Store> stores;

    public StorePersistenceStub(){
        stores = new ArrayList<>();

        //just a few stores for the stub db
        stores.add(new Store("walm035","Walmart", "35 Lakewood Blvd Winnipeg"));
        stores.add(new Store("walm157","Walmart", "1576 Regent Ave Winnipeg"));

        stores.add(new Store("cost149","Costco", "1499 Regent Ave W Winnipeg"));
        stores.add(new Store("cost236","Costco", "2365 McGillivray Blvd Winnipeg"));

        stores.add(new Store("safe202","Safeway", "2025 Corydon Ave Winnipeg"));
        stores.add(new Store("safe655","Safeway", "655 Osborne Winnipeg"));
    }

    @Override
    public List<Store> searchStoresByName(String storeName) {
        storeName = storeName.toLowerCase();
        List<Store> results;
        List<Store> storeList = getAllStores();

        if (storeName.equals("")) {     // Set store list to empty
            results = new ArrayList<>();
        }
        else {
            results = matchStores(storeName, storeList);
        }

        return results;
    }

    private List<Store> matchStores(String storeWanted, List<Store> stores) {
        List<Store> results = new ArrayList<Store>();
        storeWanted = storeWanted.toLowerCase();

        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            String storeName = store.getStoreName().toLowerCase();
            boolean match = true;

            // Check if all words in the storeName matches with user input
            for (int j = 0; j < storeWanted.length() && match; j++) {
                if (storeWanted.charAt(j) != storeName.charAt(j)) {
                    match = false;
                }
            }

            if (match) {
                results.add(store);
            }
        }

        return results;
    }
    

    @Override
    public List<Store> getAllStores() {
        return Collections.unmodifiableList(stores);
    }
    }