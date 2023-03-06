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
    /*
    @Override
    public List<Store> getStore(Store toGet) {
        List<Store> toReturn = new ArrayList<>();

        int index = stores.indexOf(toGet);
        if(index >= 0) {
            toReturn.add(stores.get(index));
        }

        return toReturn;
    }
    */



    @Override
    public List<Store> getAllStores() {
        return Collections.unmodifiableList(stores);
    }
    }