

package comp3350.GoCart.tests.business;




import junit.framework.TestCase;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessStores;


import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;


public class CalculateCheapestStoreTest extends TestCase
{

    private List<Product> products;
    private List<Store> stores;
    private Store result;

    public CalculateCheapestStoreTest()
    {
        super();
    }

    @Test
    public void testValidData(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: valid data ");

        stores = new ArrayList<>();
        stores.add(new Store("StoreMart","50 Lakewood Blvd Winnipeg"));
        Store priceChange = new Store("Walmart","35 Lakewood Blvd Winnipeg");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<>();
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        products.add(new Product("Beef Jerkey"));
        products.add(new Product("12 cookies"));
        products.add(new Product("toilet paper"));
        result = accStores.getCheapestStore(products,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("Second Store is cheaper" , priceChange,result);
        System.out.println("End Test: valid data \n");

    }
/*

    @Test
    public void testNullProductList(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: null product list ");
        stores = new ArrayList<>();
         stores.add(new Store("StoreMart","50 Lakewood Blvd Winnipeg"));

        result = accStores.getCheapestStore(null,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("no store returned/no products given",result.getStoreName()," ");
        System.out.println("End Test: null product list \n");

    }


    @Test
    public void testEmptyProductList(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: empty product list ");
        stores = new ArrayList<>();
        stores.add(new Store("StoreMart","50 Lakewood Blvd Winnipeg"));
        products = new ArrayList<>();
        result = accStores.getCheapestStore(products,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("no store returned/no products given",result.getStoreName()," ");
        System.out.println("End Test: empty product list \n");
    }



    @Test
    public void testNullProductObject(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: null product object ");
        stores = new ArrayList<>();
        stores.add(new Store("StoreMart","50 Lakewood Blvd Winnipeg"));
        Store priceChange = new Store("PigglyWiggly","90 Lakewood Blvd Winnipeg");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<>();
        products.add(null);
        result = accStores.getCheapestStore(products,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("no store returned/no product given",result.getStoreName()," ");
        System.out.println("End Test: null product object \n");
    }

    // zero products found in store stock
    // unlikely to happens as we will pull product list from internal database
    @Test
    public void testInvalidProductName(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: Invalid product name ");
        stores = new ArrayList<>();
        stores.add(new Store("StoreMart","50 Lakewood Blvd Winnipeg"));
        Store priceChange = new Store("PigglyWiggly","90 Lakewood Blvd Winnipeg");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<>();
        products.add(new Product("Pepper"));
        products.add(new Product("Pokemon Trading Cards"));
        result = accStores.getCheapestStore(products,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("no store returned/wrong products given",result.getStoreName()," ");

        System.out.println("End Test: Invalid product name \n");
    }


    // some matching products, gives result for matches
    // unlikely to happens as we will pull product list from internal database
    @Test
    public void testSomeInvalidProductName(){
        AccessStores accStores = new AccessStores();
        System.out.println("Start Test: some wrong product name ");
        products = new ArrayList<>();
        products.add(new Product("Pepper"));
        products.add(new Product("Pokemon Trading Cards"));
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        result = accStores.getCheapestStore(products,"50 shoreview bay");
        assertNotNull(result);
        assertEquals("First Store is cheapest",result.getStoreName(),"Walmart");
        System.out.println("End Test: some wrong product name \n");
    }
    */

}
