package comp3350.GoCart.business;


import java.math.BigDecimal;
import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
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
            nearest = new ArrayList<Store>();
        }

        return nearest; //return original stores if there was an error.
    }


    /*
     * takes product list and store list and returns which store has cheapest total price of items
     * items not found in store stock are not included in price.
     */
    public Store getCheapestStore(List<Product> productList,String Location){
        List<Store> storeList =  getNearestStores(Location);


        for (int i = 0; i < storeList.size();i++){
            System.out.println(storeList.get(i).toString());

        }

        int currentCheapestIndex = 0;
        BigDecimal total;
        BigDecimal currentCheapestTotal = new BigDecimal("0");
        Store cheapestStore = new Store(" ", " ");
        if(productList != null && storeList != null
                && productList.size() != 0 && storeList.size() != 0) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i) != null) {
                    total = calculateTotal(productList, storeList.get(i));
                    if (currentCheapestTotal.equals(BigDecimal.ZERO)
                            && total.compareTo(BigDecimal.ZERO) > 0
                            ||total.compareTo(currentCheapestTotal) == -1 ){
                        currentCheapestIndex = i;
                        currentCheapestTotal = total;
                    }
                    if (!total.equals(BigDecimal.ZERO))
                        cheapestStore = storeList.get(currentCheapestIndex);
                }
            }
        }
        return cheapestStore;
    }


    //Calculates Total price at given store for list of products
    private BigDecimal calculateTotal(List<Product> currentProducts, Store currentStore){
        List<Product> storesProducts = currentStore.getStoreProducts();
        BigDecimal runningTotal = new BigDecimal("0");
        for (int i = 0; i < currentProducts.size();i++){
            // second loop will be removed with sql query
            for (int j = 0; j < storesProducts.size(); j++){
                if ( currentProducts.get(i) != null) {
                    if (currentProducts.get(i).getProductName().equals(storesProducts.get(j).getProductName()))
                        runningTotal = runningTotal.add(storesProducts.get(j).getProductPrice());
                }
            }
        }
        return runningTotal;
    }

}
