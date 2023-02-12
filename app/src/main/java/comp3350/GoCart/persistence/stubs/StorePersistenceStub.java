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
        stores.add(new Store("Walmart", "35 Lakewood Blvd Winnipeg"));
        stores.add(new Store("Walmart", "1576 Regent Ave Winnipeg"));

        stores.add(new Store("Costco", "1499 Regent Ave W Winnipeg"));
        stores.add(new Store("Costco", "2365 McGillivray Blvd Winnipeg"));

        stores.add(new Store("Safeway", "2025 Corydon Ave Winnipeg"));
        stores.add(new Store("Safeway", "655 Osborne Winnipeg"));
    }

    @Override
    public List<Store> getStore(Store toGet) {
        List<Store> toReturn = new ArrayList<>();

        int index = stores.indexOf(toGet);
        if(index >= 0) {
            toReturn.add(stores.get(index));
        }

        return toReturn;
    }

    // int or store, store seems awkward
    @Override
    public List<Store> getAllStores() {
        return Collections.unmodifiableList(stores);
    }
    }