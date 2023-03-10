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

    public AccessStoreProduct(StoreProductPersistence storeProductPersistence){
        this();
        this.storeProductPersistence = storeProductPersistence;
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

    public StoreProduct findCheapestStore(List<Product> productList,List<Integer> quant, List<Store> storeList){
        int currentCheapestIndex = 0;
        BigDecimal total;
        BigDecimal currentCheapestTotal = new BigDecimal("0");
        Store cheapestStore = new Store("Emptystore", "","");
        Product newProduct = new Product("","");
        StoreProduct result = new StoreProduct(cheapestStore,newProduct,currentCheapestTotal);

        if(productList != null && storeList != null
                && productList.size() != 0 && storeList.size() != 0) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i) != null) {
                    total = calculateTotal(productList,quant, storeList.get(i).getStoreID());
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
    public BigDecimal calculateTotal(List<Product> currentProducts,List<Integer> quant, String storeID){
        //List<Product> storesProducts = currentStore.getStoreProducts();
        StoreProduct currentSP = null;

        List<StoreProduct> spList = storeProductPersistence.getStoreProducts(storeID);
        Product current = null;

        BigDecimal runningTotal = new BigDecimal("0");

        for (int i = 0; i < currentProducts.size();i++){
            if (quant != null && quant.size() > 0 ) {

                //spList= storeProductPersistence.getStoreProductByName(storeID, currentProducts.get(i).getProductName());
                for(int j = 0; j < spList.size();j++){

                    if ( spList.get(j).getProductName().equals(currentProducts.get(i).getProductName())) {
                        currentSP = spList.get(j);
                        runningTotal = runningTotal.add(currentSP.getPrice().multiply(BigDecimal.valueOf(quant.get(i))));
                    }
                }
            }
        }

        return runningTotal;
    }


}
