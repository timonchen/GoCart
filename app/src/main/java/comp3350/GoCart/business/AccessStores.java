package comp3350.GoCart.business;

/*
This is the access Stores class and deals with all logic needed for the stores in our app
IT interacts with the DB through the storePressistence interface
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.StorePersistence;

public class AccessStores{

    
    private StorePersistence storePersistence;
    private List<Store> stores;
    private DistanceCalculator calculator;



    public AccessStores(){
        storePersistence = Services.getStorePersistence();
        calculator = new DistanceCalculatorAPI(); //using the API
        stores = null;
    }

    public AccessStores(final StorePersistence storePersistence) {
        this();
        this.storePersistence = storePersistence;
    }

    public List<Store> getStores() {
        stores = storePersistence.getAllStores();
        return Collections.unmodifiableList(stores);
    }

    public Store getStoreByID(String id) {
        stores = getStores();
        Store matchingStore = null;

        for (int i = 0; i < stores.size() && matchingStore == null; i++) {
            Store currStore = stores.get(i);

            if (currStore.getStoreID().equals(id))
                matchingStore = currStore;
        }

        return matchingStore;
    }

    public List<Store> getStoresByName(String storeName) {
        stores = getStores();
        List<Store> results = storePersistence.getAllStores();
        storeName = storeName.toLowerCase();

        if (storeName.equals("")) {     // Set store list to empty
            results = new ArrayList<>();
        }
        else {
            results = matchStores(storeName, stores);
        }

        return Collections.unmodifiableList(results);
    }

    // Given a list of stores, get all elements in the list which has the same name as storeWanted
    private List<Store> matchStores(String storeWanted, List<Store> stores) {
        List<Store> results = new ArrayList<>();
        storeWanted = storeWanted.toLowerCase();    // store name of the store that the user wants

        // Go through all stores and
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

    /*
    * This method calls the calculators calculate nearest stores method.
    * Currently we have two ways ways of doing this, one is just a random distance to each store
    * The second is an actual api call to google maps api to get the real distance
    * If an error is thrown currently it just returns the orignal stub list of stores
    * Otherwise it returns a sorted list of stores by distance.
    * */
    public List<Store> getNearestStores(String location) {

        stores = storePersistence.getAllStores();
        List<Store> nearest = null;
        try{
            //executor service is used as network activity can not be done on the main thread
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<List> callable = new Callable<List>() {
                @Override
                public List call() throws Exception {
                    return calculator.calculateNearestStores(location, stores);
                }
            };
            Future<List> future = executor.submit(callable);
            nearest = future.get();
        }catch (Exception e) { //currently if we have an error we just set the distance the 0
            nearest = new ArrayList<Store>();
        }
        return nearest; //return original stores if there was an error.
    }

}
