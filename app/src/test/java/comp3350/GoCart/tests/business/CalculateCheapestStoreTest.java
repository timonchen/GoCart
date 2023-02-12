package comp3350.GoCart.tests.business;



import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.calculateCheapestStore;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;


public class CalculateCheapestStoreTest extends TestCase
{

    private List<Product> products;
    private List<Store> stores;
    private Store result;

    public CalculateCheapestStoreTest()
    {

    }

    @Test
    public void testValidData(){
        System.out.println("Start Test: valid data ");

        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        Store priceChange = new Store("PigglyWiggly","456 real rd");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<Product>();
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        products.add(new Product("Beef Jerkey"));
        products.add(new Product("12 cookies"));
        products.add(new Product("toilet paper"));
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("Second store with alternate prices is cheaper" , priceChange.equals(result));
        System.out.println("End Test: valid data \n");

    }
    @Test
    public void testNullStoreList(){
        System.out.println("Start Test: null store list ");
        products = new ArrayList<Product>();
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        result = calculateCheapestStore.returnCheapestStore(products,null);
        assertNotNull(result);
        assertTrue("no store returned/no stores given",result.getStoreName().equals(" "));
        System.out.println("End Test: null store list \n");

    }

    @Test
    public void testNullProductList(){
        System.out.println("Start Test: null product list ");
        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        result = calculateCheapestStore.returnCheapestStore(null,stores);
        assertNotNull(result);
        assertTrue("no store returned/no products given",result.getStoreName().equals(" "));
        System.out.println("End Test: null product list \n");

    }

    @Test
    public void testEmptyStoreList(){
        System.out.println("Start Test: empty store list ");
        stores = new ArrayList<Store>();
        products = new ArrayList<Product>();
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("no store returned/no stores given" ,result.getStoreName().equals(" "));
        System.out.println("End Test: empty store list \n");
    }

    @Test
    public void testEmptyProductList(){
        System.out.println("Start Test: empty product list ");
        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        products = new ArrayList<Product>();
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("no store returned/no products given",result.getStoreName().equals(" "));
        System.out.println("End Test: empty product list \n");
    }

    @Test
    public void testNullStoreObject(){
        System.out.println("Start Test: null store object ");
        stores = new ArrayList<Store>();
        stores.add(null);
        products = new ArrayList<Product>();
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("no store returned/no stores given" ,result.getStoreName().equals(" "));
        System.out.println("End Test: null store object \n");
    }


    @Test
    public void testNullProductObject(){
        System.out.println("Start Test: null product object ");
        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        Store priceChange = new Store("PigglyWiggly","456 real rd");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<Product>();
        products.add(null);
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("no store returned/no product given",result.getStoreName().equals(" "));
        System.out.println("End Test: null product object \n");
    }

    // zero products found in store stock
    // unlikely to happens as we will pull product list from internal database
    @Test
    public void testInvalidProductName(){
        System.out.println("Start Test: Invalid product name ");
        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        Store priceChange = new Store("PigglyWiggly","456 real rd");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<Product>();
        products.add(new Product("Pepper"));
        products.add(new Product("Pokemon Trading Cards"));
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("no store returned/wrong products given",result.getStoreName().equals(" "));

        System.out.println("End Test: Invalid product name \n");
    }

    // some matching products, gives result for matches
    // unlikely to happens as we will pull product list from internal database
    @Test
    public void testSomeInvalidProductName(){
        System.out.println("Start Test: some wrong product name ");
        stores = new ArrayList<Store>();
        stores.add(new Store("StoreMart","123 fake st"));
        Store priceChange = new Store("PigglyWiggly","456 real rd");
        priceChange.getProductsStubForTesting().productAlternatePrices();
        stores.add(priceChange);
        products = new ArrayList<Product>();
        products.add(new Product("Pepper"));
        products.add(new Product("Pokemon Trading Cards"));
        products.add(new Product("Banana"));
        products.add(new Product("Rye Bread"));
        result = calculateCheapestStore.returnCheapestStore(products,stores);
        assertNotNull(result);
        assertTrue("First Store is cheapest", result.getStoreName().equals("StoreMart"));
        System.out.println("End Test: some wrong product name \n");
    }
}
