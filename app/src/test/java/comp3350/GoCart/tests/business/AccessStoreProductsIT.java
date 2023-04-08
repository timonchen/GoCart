package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StoreProductPersistence;
import comp3350.GoCart.persistence.hsqldb.StoreProductPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;


public class AccessStoreProductsIT {
    private AccessStoreProduct accessStoreProduct;
    private File tempDB;

    @Before
    public void init() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final StoreProductPersistence persistence = new StoreProductPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessStoreProduct = new AccessStoreProduct(persistence);
    }

    @Test
    public void testGetStoreProdcutsList(){
        final List<StoreProduct> storeProducts;
        storeProducts = accessStoreProduct.getStoresProducts("1");
        assertEquals(8,storeProducts.size());
    }

    @Test
    public void testGetByName(){
        final List<StoreProduct> storeProducts;
        storeProducts = accessStoreProduct.getStoreProductsByName("1","Banana");
        assertEquals(1,storeProducts.size());
        assertTrue("Banana".equals(storeProducts.get(0).getProductName()));
    }

    @Test
    public void testGetByNameWithAllergen(){

        final List<StoreProduct> storeProductsWith;
        storeProductsWith = accessStoreProduct.getStoreProductsByNameWithAllergen("1","Lucky Charms");
        assertEquals("Product has allergen, so nothing is returned",0,storeProductsWith.size());
    }

    //find findCheapestStore() does not directly interact with DB, only through calls through
    //calculateTotal(), so has no integration tests.

    @Test
    public void testCalculateTotal(){
        final Store store = new Store("1");
        final Product p1 = new Product("4521","Banana",false,"produce");
        List<Integer> quant = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        quant.add(1);
        products.add(p1);

        assertEquals(0,accessStoreProduct.calculateTotal(products,quant,store).compareTo(BigDecimal.valueOf(1.05)));
    }

    @After
    public void terminate() {
        // reset DB
        this.tempDB.delete();
    }
}

