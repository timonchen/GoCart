package comp3350.GoCart.business;

import java.math.BigDecimal;
import java.nio.file.ClosedFileSystemException;
import java.util.List;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

//likely to  be moved to different class in future, possibly checkout

public class calculateCheapestStore {

    //
    public calculateCheapestStore(){
    }

    /*
    * takes product list and store list and returns which store has cheapest total price of items
    * items not found in store stock are not included in price.
    */
    public static Store returnCheapestStore(List<Product> productList, List<Store> storeList){
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
    private static BigDecimal calculateTotal(List<Product> currentProducts, Store currentStore){
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
