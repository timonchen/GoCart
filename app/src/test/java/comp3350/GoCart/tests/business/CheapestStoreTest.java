package comp3350.GoCart.tests.business;


import static org.mockito.Mockito.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.*;
import comp3350.GoCart.objects.*;
import comp3350.GoCart.persistence.StoreProductPersistence;


public class CheapestStoreTest{

    private List<Product> products;
    private StoreProduct result;
    private AccessStoreProduct accessStoreProduct;
    private Product product1,product2;
    private List<Store> storeList;
    private Store store1,store2;
    private StoreProduct store1Product1,store1Product2,store2Product1,store2Product2;
    private List<StoreProduct> store1ProductList,store2ProductList;
    private StoreProductPersistence mockStoreProductPersistence;

    public CheapestStoreTest()
    {
        super();
    }
    private List<Integer> quant;

    @Before
    public void initalize(){

        store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");
        store2 = new Store("2","Costco", "1499 Regent Ave W Winnipeg");
        product1 = new Product("4521","Banana",false,"produce");
        product2 = new Product("6849","Rye Bread",true, "bakery");

        store1Product1 = new StoreProduct(store1,product1,new BigDecimal(1.00));
        store1Product2 = new StoreProduct(store1,product2,new BigDecimal(2.00));

        store2Product1 = new StoreProduct(store2,product1,new BigDecimal(1.10));
        store2Product2 = new StoreProduct(store2,product2,new BigDecimal(2.10));

        store1ProductList = new ArrayList<>();
        store1ProductList.add(store1Product1);
        store1ProductList.add(store1Product2);

        store2ProductList = new ArrayList<>();
        store2ProductList.add(store2Product1);
        store2ProductList.add(store2Product2);

        storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);

        mockStoreProductPersistence = mock(StoreProductPersistence.class);
        accessStoreProduct = new AccessStoreProduct(mockStoreProductPersistence);

        when(mockStoreProductPersistence.getStoreProducts("1")).thenReturn(store1ProductList);
        when(mockStoreProductPersistence.getStoreProducts("2")).thenReturn(store2ProductList);

        quant = new ArrayList<>();
        quant.add(1);
        products = new ArrayList<>();

        System.out.println("Init executed");

    }



    @Test
    public void testValidData(){
        System.out.println("Start Test:  valid data ");

        products.add(product1);
        quant.add(1);

        result = accessStoreProduct.findCheapestStore(products,quant,storeList);
        assertNotNull(result);
        assertEquals("Store 1 is cheapest" , "1",result.getStoreId());
        assertEquals("should equal ",new BigDecimal("1.00")  , result.getPrice() );
        System.out.println("End Test: valid data \n");
        verify(mockStoreProductPersistence).getStoreProducts("1");
        verify(mockStoreProductPersistence).getStoreProducts("2");


    }


    @Test
    public void testNullStoreList(){


        System.out.println("Start Test: null store list ");

        products.add(product1);
        quant.add(1);

        result = accessStoreProduct.findCheapestStore(products,quant,null);
        System.out.println("return ");
        assertNotNull(result);
        assertEquals("no store returned/no stores given","Emptystore",result.getStoreId());
        System.out.println("End Test: null store list \n");


    }

    @Test
    public void testNullProductList(){


        System.out.println("Start Test: null product list ");
        quant.add(1);

        result = accessStoreProduct.findCheapestStore(null,quant,storeList);
        assertNotNull(result);
        assertEquals("no store returned/no products given","Emptystore",result.getStoreId());
        System.out.println("End Test: null product list \n");


    }

    @Test
    public void testEmptyStoreList(){
        System.out.println("Start Test: empty store list ");
        storeList = new ArrayList<>();

        result = accessStoreProduct.findCheapestStore(products,quant,storeList);
        assertNotNull(result);
        assertEquals("no store returned/no stores given" ,"Emptystore",result.getStoreId());
        System.out.println("End Test: empty store list \n");


    }

    @Test
    public void testEmptyProductList(){

        System.out.println("Start Test: empty product list ");
        products = new ArrayList<>();

        result = accessStoreProduct.findCheapestStore(products,quant,storeList);
        assertNotNull(result);
        assertEquals("no store returned/no products given","Emptystore",result.getStoreId());
        System.out.println("End Test: empty product list \n");

    }

    @Test
    public void testSomeInvalidProductName(){
        System.out.println("Start Test: some wrong product name ");
        quant.add(1);
        quant.add(1);
        quant.add(1);

        products = new ArrayList<>();

        products.add(new Product( "4521","Banana",false, "produce"));
        products.add(new Product( "6849","Rye Bread", true,"bakery"));
        products.add(new Product( "6849","Rye Bread", true,"bakery"));
        products.add(new Product( "1234","Pokeman TCG", false,null));
        products.add(new Product( "7890","one dozen dabloons", false, null));
        result = accessStoreProduct.findCheapestStore(products,quant,storeList);
        assertNotNull(result);
        assertEquals("First Store is cheapest","1",result.getStoreId());
        System.out.println("End Test: some wrong product name \n");
        verify(mockStoreProductPersistence).getStoreProducts("1");
        verify(mockStoreProductPersistence).getStoreProducts("2");
    }


}



