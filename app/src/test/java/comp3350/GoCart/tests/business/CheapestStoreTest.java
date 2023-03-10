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
    private List<Integer> quant;

    @Before
    public void initalize(){
        //accP.getProducts();
        accS.getStores();
         quant = new ArrayList<>();
        quant.add(1);
        quant.add(2);
        quant.add(3);
        quant.add(4);
        quant.add(5);
        quant.add(6);
        quant.add(7);
        quant.add(8);


    }



    @Test
    public void testValidData(){
        initalize();
        result = accSP.findCheapestStore(accP.getProducts(),quant,accS.getStores());
        assertNotNull(result);
        assertEquals("costco 149 is cheapest" , "cost149",result.getStoreId());
        assertEquals("should equal " , result.getPrice(), new BigDecimal("309.00")  );
        System.out.println("End Test: valid data \n");

    }


    @Test
    public void testNullStoreList(){
        initalize();
        System.out.println("Start Test: null store list ");

        result = accSP.findCheapestStore(accP.getProducts(),quant,null);
        assertNotNull(result);
        assertEquals("no store returned/no stores given","Emptystore",result.getStoreId());
        System.out.println("End Test: null store list \n");

    }

    @Test
    public void testNullProductList(){
        initalize();
        System.out.println("Start Test: null product list ");
        result = accSP.findCheapestStore(null,quant,accS.getStores());
        assertNotNull(result);
        assertEquals("no store returned/no products given","Emptystore",result.getStoreId());
        System.out.println("End Test: null product list \n");

    }

    @Test
    public void testEmptyStoreList(){
        initalize();
        System.out.println("Start Test: empty store list ");
        stores = new ArrayList<>();
        result = accSP.findCheapestStore(accP.getProducts(),quant,stores);
        assertNotNull(result);
        assertEquals("no store returned/no stores given" ,"Emptystore",result.getStoreId());
        System.out.println("End Test: empty store list \n");
    }

    @Test
    public void testEmptyProductList(){
        initalize();
        System.out.println("Start Test: empty product list ");
        products = new ArrayList<>();
        result = accSP.findCheapestStore(products,quant,accS.getStores());
        assertNotNull(result);
        assertEquals("no store returned/no products given","Emptystore",result.getStoreId());
        System.out.println("End Test: empty product list \n");
    }

    @Test
    public void testSomeInvalidProductName(){
        initalize();
        System.out.println("Start Test: some wrong product name ");

        products = new ArrayList<>();

        products.add(new Product( "4521","Banana",false));
        products.add(new Product( "6849","Rye Bread", true));
        products.add(new Product( "1234","Pokeman TCG", false));
        products.add(new Product( "7890","one dozen dabloons", false));
        result = accSP.findCheapestStore(products,quant,accS.getStores());
        assertNotNull(result);
        assertEquals("First Store is cheapest","cost149",result.getStoreId());
        System.out.println("End Test: some wrong product name \n");
    }


}
