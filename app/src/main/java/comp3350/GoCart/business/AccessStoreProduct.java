package comp3350.GoCart.business;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StoreProductPersistence;

public class AccessStoreProduct {

    private StoreProductPersistence storeProductPersistence;
    private List<StoreProduct> storeProducts;



    public AccessStoreProduct(){
        storeProductPersistence = Services.getStoreProductPersistence();
        storeProducts = null;
    }

    public List<StoreProduct> getStoresProducts(String StoreID) {
        storeProducts= storeProductPersistence.getStoreProducts(StoreID);
        return Collections.unmodifiableList(storeProducts);
    }

    public StoreProduct findCheapestStore(List<Product> productList, List<Store> storeList){
        int currentCheapestIndex = 0;
        BigDecimal total;
        BigDecimal currentCheapestTotal = new BigDecimal("0");
        Store newStore = new Store("Emptystore", "","");
        Product newProduct = new Product("Emptyproduct","",false);
        StoreProduct result =  new StoreProduct(newStore,newProduct,currentCheapestTotal);

        if(productList != null && storeList != null
                && productList.size() != 0 && storeList.size() != 0) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i) != null) {
                    total = calculateTotal(productList, storeList.get(i).getStoreID());
                    if (currentCheapestTotal.equals(BigDecimal.ZERO)
                            && total.compareTo(BigDecimal.ZERO) > 0
                            ||total.compareTo(currentCheapestTotal) == -1 ){
                        currentCheapestIndex = i;
                        currentCheapestTotal = total;
                    }

                    if (!total.equals(BigDecimal.ZERO))
                        result = new StoreProduct(storeList.get(currentCheapestIndex),newProduct,currentCheapestTotal);
                }

            }
        }
        return result;
    }


    //Calculates Total price at given store for list of products
    private BigDecimal calculateTotal(List<Product> currentProducts, String storeID){
        //List<Product> storesProducts = currentStore.getStoreProducts();
        List<StoreProduct> storesProducts = getStoresProducts(storeID);
        BigDecimal runningTotal = new BigDecimal("0");

        for (int i = 0; i < currentProducts.size();i++){
            if (storesProducts.get(i) != null)
                if (storesProducts.get(i).getProductID().equals(currentProducts.get(i).getProductID()))
                    runningTotal = runningTotal.add(storesProducts.get(i).getPrice());
        }

        return runningTotal;
    }


}
