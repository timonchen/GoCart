package comp3350.GoCart.business;


import java.nio.file.attribute.AclEntry;
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

    public List<Store> getStores() {
        stores = storePersistence.getAllStores();
        return Collections.unmodifiableList(stores);
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
            for(Store store : stores) {
                store.setDistToUser(0.0);
            }
        }

        return nearest == null ? stores : nearest; //return original stores if there was an error.
    }

}
