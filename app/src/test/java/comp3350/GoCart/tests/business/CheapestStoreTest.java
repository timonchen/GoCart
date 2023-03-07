package comp3350.GoCart.tests.business;



import junit.framework.TestCase;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import comp3350.GoCart.business.*;
import comp3350.GoCart.objects.*;

import comp3350.GoCart.persistence.stubs.StoreProductPersistenceStub;


public class CheapestStoreTest extends TestCase
{

    private List<Product> products;

    private List<Store> stores;
    private List<StoreProduct> storeProduct;
    private StoreProduct result;
    private StoreProductPersistenceStub spStub;
    private AccessProducts accP = new AccessProducts();

    private AccessStores accS = new AccessStores();
    private AccessStoreProduct accSP = new AccessStoreProduct();

    public CheapestStoreTest()
    {
        super();
    }

    @Before
    public void init(){
        accP.getProducts();
        accS.getStores();

    }



    @Test
    public void testValidData(){
        System.out.println("Start Test: valid data ");
        result = accSP.findCheapestStore(accP.getProducts(),accS.getStores());
        assertNotNull(result);
        assertEquals("costco 149 is cheapest" , "cost149",result.getStoreId());
        assertTrue(result.getPrice().toString().equals("52.30"));
        System.out.println("End Test: valid data \n");

    }


    @Test
    public void testNullStoreList(){
        System.out.println("Start Test: null store list ");


        result = accSP.findCheapestStore(accP.getProducts(),null);
        assertNotNull(result);
        assertEquals("no store returned/no stores given",result.getStoreId() ,"Emptystore");
        System.out.println("End Test: null store list \n");

    }

    @Test
    public void testNullProductList(){
        System.out.println("Start Test: null product list ");
        result = accSP.findCheapestStore(null,accS.getStores());
        assertNotNull(result);
        assertEquals("no store returned/no products given",result.getStoreId() ,"Emptystore");
        System.out.println("End Test: null product list \n");

    }

    @Test
    public void testEmptyStoreList(){
        System.out.println("Start Test: empty store list ");
        stores = new ArrayList<>();
        result = accSP.findCheapestStore(accP.getProducts(),stores);
        assertNotNull(result);
        assertEquals("no store returned/no stores given" ,result.getStoreId() ,"Emptystore");
        System.out.println("End Test: empty store list \n");
    }

    @Test
    public void testEmptyProductList(){
        System.out.println("Start Test: empty product list ");
        products = new ArrayList<>();
        result = accSP.findCheapestStore(products,accS.getStores());
        assertNotNull(result);
        assertEquals("no store returned/no products given",result.getStoreId() ,"Emptystore");
        System.out.println("End Test: empty product list \n");
    }

    @Test
    public void testSomeInvalidProductName(){
        System.out.println("Start Test: some wrong product name ");


        products = new ArrayList<>();

        products.add(new Product( "4521","Banana",false));
        products.add(new Product( "6849","Rye Bread", true));
        products.add(new Product( "1234","Pokeman TCG", false));
        products.add(new Product( "7890","one dozen dabloons", false));
        result = accSP.findCheapestStore(products,accS.getStores());
        assertNotNull(result);
        assertEquals("First Store is cheapest",result.getStoreId() ,"cost149");
        System.out.println("End Test: some wrong product name \n");
    }


}
