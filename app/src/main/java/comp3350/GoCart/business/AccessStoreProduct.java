package comp3350.GoCart.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;

public class AccessStoreProduct {


    private StoreProductPersistence storeProductPersistence;
    private List<StoreProduct> storeProducts;
    private AccessProducts accessProducts;



    public AccessStoreProduct(){
        storeProductPersistence = Services.getStoreProductPersistence();
        accessProducts = new AccessProducts();
        storeProducts = null;
    }

    public AccessStoreProduct(final StoreProductPersistence storePersistence) {
        this();
        this.storeProductPersistence = storePersistence;
    }

    public List<StoreProduct> getStoresProducts(String StoreID) {
        storeProducts= storeProductPersistence.getStoreProducts(StoreID);
        return Collections.unmodifiableList(storeProducts);
    }

    public List<StoreProduct> getStoreProductsByName(String storeID, String productName) {
        storeProducts = getStoresProducts(storeID);
        List<StoreProduct> matchingProducts = new ArrayList<>();

        if (productName != null && storeID != null)
        {
            for (int i = 0; i < storeProducts.size(); i++)
            {
                StoreProduct currStoreProduct = storeProducts.get(i);

                if (currStoreProduct.getStoreId().equals(storeID) && currStoreProduct.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                    matchingProducts.add(currStoreProduct);
                }
            }
        }

        return matchingProducts;
    }

    public List<StoreProduct> getStoreProductsByNameWithAllergen(String storeID, String productName) {
        List<StoreProduct> unfilteredProducts = getStoreProductsByName(storeID, productName);
        List<StoreProduct> filteredProducts = new ArrayList<>();
        List<Product> productsWithAllergen = accessProducts.getDietaryProducts();

        // For each product with an allergen, compare it with every product in the unfiltered products
        for (int i = 0; i < productsWithAllergen.size(); i++) {
            boolean foundProduct = false;

            for (int j = 0; j < unfilteredProducts.size() && !foundProduct; j++) {
                StoreProduct curr = unfilteredProducts.get(j);

                if (productsWithAllergen.get(i).getProductID().equals(curr.getProductID()))
                {
                    filteredProducts.add(curr);
                    unfilteredProducts.remove(j);
                }
            }
        }

        return filteredProducts;
    }

    public Store returnCheapestStore(List<Product> productList, List<Store> storeList){
        int currentCheapestIndex = 0;
        BigDecimal total;
        BigDecimal currentCheapestTotal = new BigDecimal("0");
        Store cheapestStore = new Store("ERROR store", "","");
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
                        cheapestStore = storeList.get(currentCheapestIndex);
                }
            }
        }
        return cheapestStore;
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
