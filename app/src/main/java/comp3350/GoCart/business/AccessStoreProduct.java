package comp3350.GoCart.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.EmptyStore;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.ProductPersistence;
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

    // Constructor with mock StoreProductPersistence and mock ProductPersistence
    public AccessStoreProduct(StoreProductPersistence storeProductPersistence, ProductPersistence productPersistence){
        this.storeProductPersistence = storeProductPersistence;
        accessProducts = new AccessProducts(productPersistence);
        storeProducts = null;
    }

    public List<StoreProduct> getStoresProducts(String StoreID) {
        storeProducts= storeProductPersistence.getStoreProducts(StoreID);
        return Collections.unmodifiableList(storeProducts);
    }

    /*
    returns all StoreProducts with a matching prdoduct name and storeID combination
     */
    public List<StoreProduct> getStoreProductsByName(String storeID, String productName) {
        storeProducts = getStoresProducts(storeID);
        List<StoreProduct> matchingProducts = new ArrayList<>();

        if (productName != null && storeID != null)
        {
            // Go through all storeProducts with storeID, and look for storeProducts with the matching productName
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

    // Get StoreProductByName given a category
    public List<StoreProduct> getStoreProductsByName(String storeID, String productName, String categoryName) {
        List<StoreProduct> matchingName = getStoreProductsByName(storeID, productName);    // Get StoreProducts with the matching name
        return categorizeStoreProducts(matchingName, categoryName);
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

    // Get StoreProductByNameWithAllergen given a category
    public List<StoreProduct> getStoreProductsByNameWithAllergen(String storeID, String productName, String categoryName) {
        List<StoreProduct> matchingName = getStoreProductsByNameWithAllergen(storeID, productName);    // Get StoreProducts with the matching name
        return categorizeStoreProducts(matchingName, categoryName);
    }

    // Given a list of StoreProduct, this method will search for a StoreProduct in the list that contains a Product with a category we want
    private List<StoreProduct> categorizeStoreProducts(List<StoreProduct> storeProducts, String categoryName) {
        List<Product> matchingCategory = accessProducts.productCatogory(categoryName); // Products with a matching category
        List<StoreProduct> result = new ArrayList<>();

        // For every StoreProduct in storeProducts, see if the StoreProduct's Product matches a Product in matchingCategory
        for (int i = 0; i < storeProducts.size(); i++) {
            boolean foundProduct = false;
            StoreProduct curr = storeProducts.get(i);

            for (int j = 0; j < matchingCategory.size() && !foundProduct; j++) {
                if (curr.getProductName().equals(matchingCategory.get(j).getProductName())) {   // Compare product names
                    result.add(curr);
                }
            }
        }

        return result;
    }

    //calls findCheapestStore() to calculate price for each store given, and returns cheapest store
    public StoreProduct findCheapestStore(List<Product> productList,List<Integer> quant, List<Store> storeList){
        int currentCheapestIndex = 0;
        BigDecimal total;
        BigDecimal currentCheapestTotal = new BigDecimal("0");

        Store cheapestStore = new EmptyStore();
        Product newProduct = new Product("","");
        StoreProduct result = new StoreProduct(cheapestStore,newProduct,currentCheapestTotal);

        if(productList != null && storeList != null
                && productList.size() != 0 && storeList.size() != 0) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i) != null) {
                    total = calculateTotal(productList,quant, storeList.get(i));
                    if (currentCheapestTotal.equals(BigDecimal.ZERO)
                            && total.compareTo(BigDecimal.ZERO) > 0
                            ||total.compareTo(currentCheapestTotal) == -1 ){
                        currentCheapestIndex = i;
                        currentCheapestTotal = total;
                    }
                    if (!total.equals(BigDecimal.ZERO)) {
                        result = new StoreProduct(storeList.get(currentCheapestIndex), newProduct, currentCheapestTotal);

                    }
                }
            }
        }

        return result;
    }

    // Variation of findCheapestStore
    // Given a product, this method will find the cheapest store that sells that product
    public StoreProduct findCheapestStore(Product product) {
        List<StoreProduct> storeProducts = findAllProducts(product);    // List containing all variations of a product
        StoreProduct cheapestStore = null;

        // Loop through all the storeProducts to find the cheapest storeProduct
        int length = storeProducts.size();
        for (int i = 0; i < length; i++) {
            StoreProduct curr = storeProducts.get(i);

            if (cheapestStore == null || (cheapestStore.getPrice().compareTo(curr.getPrice()) == 1)) {  // if .compareTo() returns 1, then cheapestStore's price is more expensive than curr
                cheapestStore = curr;
            }
        }

        return cheapestStore;
    }


    //Calculates Total price at given store for list of products
    public BigDecimal calculateTotal(List<Product> currentProducts,List<Integer> quant, Store store){

        StoreProduct currentSP = null;
        List<StoreProduct> spList = new ArrayList<>();
        if(!(store instanceof EmptyStore)){
            spList = storeProductPersistence.getStoreProducts(store.getStoreID());
        }
        Product current = null;

        BigDecimal runningTotal = new BigDecimal("0");

        for (int i = 0; i < currentProducts.size();i++){
            if (quant != null && quant.size() > 0 ) {
                for(int j = 0; j < spList.size();j++){
                    if ( spList.get(j).getProductName().equals(currentProducts.get(i).getProductName())) {
                        currentSP = spList.get(j);
                        runningTotal = runningTotal.add(currentSP.getPrice().multiply(BigDecimal.valueOf(quant.get(i))));
                    }
                }
            }
        }

        runningTotal= runningTotal.setScale(2, RoundingMode.FLOOR);
        return runningTotal;
    }

    /* All stores sell a product at a different price. Although the stores are selling the same product, their pices vary amongst stores.
     * The purpose of this method is to get all the possible variants of a product
     */
    private List<StoreProduct> findAllProducts(Product product) {
        List<StoreProduct> storeProducts = storeProductPersistence.getAllStoreProducts();   // List of all StoreProducts
        List<StoreProduct> matchingStoreProducts = new ArrayList<StoreProduct>();    // List of all StoreProducts with a matching product as the parameter

        // Get a list of all storeProducts that match with product
        int length = storeProducts.size();
        for (int i = 0; i < length; i++) {
            StoreProduct curr = storeProducts.get(i);   // Current storeProduct

            // StoreProducts of the same Product have the same name, so use that in the comparison
            if (product.getProductName().equals(curr.getProductName())) {
                matchingStoreProducts.add(curr);
            }
        }

        return matchingStoreProducts;
    }

    public BigDecimal findAveragePrice(Product product) {
        BigDecimal avgPrice = new BigDecimal(0);
        List<StoreProduct> matchingStoreProducts = findAllProducts(product);

        // Add up all the prices
        int length = matchingStoreProducts.size();
        for (int i = 0; i < length; i++) {
            avgPrice = avgPrice.add(matchingStoreProducts.get(i).getPrice());
        }

        // Divide by length and round down to 2 decimal places to find average
        avgPrice = avgPrice.divide(BigDecimal.valueOf(length), 2, RoundingMode.DOWN);

        return avgPrice;
    }

}
