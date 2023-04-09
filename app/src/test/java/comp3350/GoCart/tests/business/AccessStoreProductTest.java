package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StoreProductPersistence;


public class AccessStoreProductTest {

    private List<Product> products;
    private StoreProduct result;
    private AccessStoreProduct accessStoreProduct;
    private Product product1,product2,product3;
    private List<Store> storeList;
    private Store store1,store2;
    private StoreProduct storeProduct1,storeProduct2,storeProduct3;
    private List<StoreProduct> store1ProductList,store2ProductList,storeProducts;
    private StoreProductPersistence mockStoreProductPersistence;

    private List<Integer> quant;

    @Before
    public void initalize(){

        store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");
        product1 = new Product("4521","Banana",false,"produce");
        product2 = new Product("6849","Rye Bread",true, "bakery");
        product3 = new Product("6917", "Whole Wheat Bread",true,"bakery");

        storeProduct1 = new StoreProduct(store1,product1,new BigDecimal(1.00));
        storeProduct2 = new StoreProduct(store1,product2,new BigDecimal(2.00));
        storeProduct3 = new StoreProduct(store1,product3,new BigDecimal(3.00));

        store1ProductList = new ArrayList<>();
        store1ProductList.add(storeProduct1);
        store1ProductList.add(storeProduct2);
        store1ProductList.add(storeProduct3);


        storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);

        mockStoreProductPersistence = mock(StoreProductPersistence.class);
        accessStoreProduct = new AccessStoreProduct(mockStoreProductPersistence);

        quant = new ArrayList<>();
        quant.add(1);



    }

    @Test
    public void testInvalidStoreValidProduct() {
        when(accessStoreProduct.getStoreProductsByName("9",anyString())).thenReturn(new ArrayList<>());
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("9", "Banana");
        assertTrue("This store should not exist", result.isEmpty());
    }

    @Test
    public void testValidStoreInValidProduct() {
        when(accessStoreProduct.getStoreProductsByName("1",anyString())).thenReturn(store1ProductList);
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "Apple");
        assertTrue("This product should not exist", result.isEmpty());
    }

    @Test
    public void testUpperAndLowerCase() {
        when(accessStoreProduct.getStoreProductsByName("1",anyString())).thenReturn(store1ProductList);
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "rYe BrEaD");
        assertTrue("The product returned should still be rye bread despite letter case", result.get(0).getProductName().equals("Rye Bread"));
    }

    @Test
    public void testPartialSearch() {
        when(accessStoreProduct.getStoreProductsByName("1",anyString())).thenReturn(store1ProductList);
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "bread");
        assertTrue("The product returned should be Rye Bread and Whole Wheat Bread", (result.get(0).getProductName().equals("Rye Bread") &&
                result.get(0).getProductName().equals("Whole Wheat Bread") || (result.get(0).getProductName().equals("Whole Wheat Bread")
                || result.get(0).getProductName().equals("Rye Bread"))));
    }
}
