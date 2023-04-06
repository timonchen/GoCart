package comp3350.GoCart.tests.business;



import static org.mockito.Mockito.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;


import junit.framework.TestCase;


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

    private List<Store> stores;
    private List<StoreProduct> storeProduct;
    private StoreProduct result;

    private AccessProducts accessProducts ;
    private AccessStores accessStores;
    private AccessStoreProduct accessStoreProduct;
    private Product prod1,prod2;
    private List<Store> storeList;
    private Store store1,store2;
    private StoreProduct sps1p1,sps1p2,sps2p1,sps2p2;
    private List<StoreProduct> s1PL,s2PL;
    private StoreProductPersistence spp;

    public CheapestStoreTest()
    {
        super();
    }
    private List<Integer> quant;

    @Before
    public void initalize(){
        accessStores = mock(AccessStores.class);
        accessProducts = mock(AccessProducts.class);

        store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");
        store2 = new Store("2","Costco", "1499 Regent Ave W Winnipeg");
        prod1 = new Product("4521","Banana",false,"produce");
        prod2 = new Product("6849","Rye Bread",true, "bakery");

        sps1p1 = new StoreProduct(store1,prod1,new BigDecimal(1.00));
        sps1p2 = new StoreProduct(store1,prod2,new BigDecimal(2.00));

        sps2p1 = new StoreProduct(store2,prod1,new BigDecimal(1.10));
        sps2p2 = new StoreProduct(store2,prod2,new BigDecimal(2.10));

        s1PL = new ArrayList<>();
        s1PL.add(sps1p1);
        s1PL.add(sps1p2);

        s2PL = new ArrayList<>();
        s2PL.add(sps2p1);
        s2PL.add(sps2p2);

        storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);

        spp = mock(StoreProductPersistence.class);
        accessStoreProduct = new AccessStoreProduct(spp);

        spp = mock(StoreProductPersistence.class);
        accessStoreProduct = new AccessStoreProduct(spp);
        when(spp.getStoreProducts("1")).thenReturn(s1PL);
        when(spp.getStoreProducts("2")).thenReturn(s2PL);

        quant = new ArrayList<>();
        quant.add(1);
        products = new ArrayList<>();

        System.out.println("Init executed");

    }



    @Test
    public void testValidData(){
        System.out.println("Start Test:  valid data ");

        products.add(prod1);
        quant.add(1);

        result = accessStoreProduct.findCheapestStore(products,quant,storeList);
        assertNotNull(result);
        assertEquals("Store 1 is cheapest" , "1",result.getStoreId());
        assertEquals("should equal ",new BigDecimal("1.00")  , result.getPrice() );
        System.out.println("End Test: valid data \n");

    }


    @Test
    public void testNullStoreList(){


        System.out.println("Start Test: null store list ");

        products.add(prod1);
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
    }


}



