package comp3350.GoCart.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class StoreTest {
    private Store store = new Store("1","testStore", "test land avenue");

    @Test
    public void testNullStore() {
        System.out.println("\nStarting testNullStore");
        assertNotNull("Store should not be null", store);

    }

    @Test
    public void testStoreName() {
        System.out.println("\nStarting testStoreName");
        assertTrue("Store name should be same as the one we are testing", "testStore".equals(store.getStoreName()));
    }

    @Test
    public void testStoreAddress() {
        System.out.println("\nStarting testStoreAddress");
        assertTrue("Store address should be same as the one we are testing", "test land avenue".equals(store.getStoreAddress()));

    }



    @Test
    public void testStoreEquals() {
        Store store2 = new Store("2", "testStore", "test land avenue");
        System.out.println("\nStarting testProductEquals");
        assertTrue("Both store should be equal", store.equals(store2));

    }

}
