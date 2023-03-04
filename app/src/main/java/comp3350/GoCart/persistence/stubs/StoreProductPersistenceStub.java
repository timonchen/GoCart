package comp3350.GoCart.persistence.stubs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;


public class StoreProductPersistenceStub implements StoreProductPersistence {

    private List<StoreProduct> sps;

    public StoreProductPersistenceStub(){
        this.sps = new ArrayList<>();

        final Product banana = new Product( "4521","Banana",false);
        final Product ryeBread = new Product( "6849","Rye Bread", true);
        final Product wheatBread= new Product( "6917","Whole Wheat Bread", true);
        final Product tp = new Product( "3984","toilet paper", false);

        final Store walm035 = new Store("walm035","Walmart", "35 Lakewood Blvd Winnipeg");
        final Store walm157 = new Store("walm157","Walmart", "1576 Regent Ave Winnipeg");
        final Store cost236 = new Store("cost236","Costco", "2365 McGillivray Blvd Winnipeg");
        final Store safe202 = new Store("safe202","Safeway", "2025 Corydon Ave Winnipeg");


        this.sps.add(new StoreProduct(walm035,banana,new BigDecimal("1.05")));
        this.sps.add(new StoreProduct(walm035,ryeBread,new BigDecimal("1.45")));
        this.sps.add(new StoreProduct(walm035,wheatBread,new BigDecimal("1.45")));
        this.sps.add(new StoreProduct(walm035,tp,new BigDecimal("15.99")));

        this.sps.add(new StoreProduct(walm157,banana,new BigDecimal("1.06")));
        this.sps.add(new StoreProduct(walm157,ryeBread,new BigDecimal("1.46")));
        this.sps.add(new StoreProduct(walm157,wheatBread,new BigDecimal("1.46")));
        this.sps.add(new StoreProduct(walm157,tp,new BigDecimal("16.00")));

        this.sps.add(new StoreProduct(cost236,banana,new BigDecimal("1.00")));
        this.sps.add(new StoreProduct(cost236,ryeBread,new BigDecimal("1.40")));
        this.sps.add(new StoreProduct(cost236,wheatBread,new BigDecimal("1.40")));
        this.sps.add(new StoreProduct(cost236,tp,new BigDecimal("15.95")));

        this.sps.add(new StoreProduct(safe202,banana,new BigDecimal("1.10")));
        this.sps.add(new StoreProduct(safe202,ryeBread,new BigDecimal("1.50")));
        this.sps.add(new StoreProduct(safe202,wheatBread,new BigDecimal("1.50")));
        this.sps.add(new StoreProduct(safe202,tp,new BigDecimal("16.10")));


    }

    //returns all stores that have product
    /*
    //may not be needed
    @Override
    public List<StoreProduct> getProductStores(String productID){
        List<StoreProduct> newStoreProducts;
        StoreProduct sp;

        newStoreProducts = new ArrayList<>();
        for (int i = 0; i < sps.size();i++){
            sp = sps.get(i);
            if (sp.getProductID().equals(productID)){
                newStoreProducts.add(sps.get(i));
            }
        }
        return newStoreProducts;



    }
     */

    //returns all products from storeID
    @Override
    public List<StoreProduct> getStoreProducts(String storeID){
        List<StoreProduct> newStoreProducts;
        StoreProduct ps;

        newStoreProducts = new ArrayList<>();
        for (int i = 0; i < sps.size();i++){
            ps = sps.get(i);
            if (ps.getStoreId().equals(storeID)){
                newStoreProducts.add(sps.get(i));
            }
        }
        return newStoreProducts;
    }
}



