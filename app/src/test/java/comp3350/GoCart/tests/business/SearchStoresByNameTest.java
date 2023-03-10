package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;

import java.util.List;

public class SearchStoresByNameTest extends TestCase {
    private AccessStores accessStores;

    public SearchStoresByNameTest() {
        super();
        accessStores = new AccessStores(new StorePersistenceStub());
    }

    // Search for a store that does not exist
    @Test
    public void testNoStoresFound() {
        List<Store> result = accessStores.getStoresByName("SuperStore");
        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testBlankStore() {
        List<Store> result = accessStores.getStoresByName("");
        assertTrue("List should be empty", result.isEmpty());
    }


    // Test multiple stores found
    @Test
    public void testMultipleStoresFound() {
        List<Store> result = accessStores.getStoresByName("Walmart");
        assertTrue("There should be 2 stores with the name Walmart", result.size() == 2);
        assertTrue("This store should be Walmart", result.get(0).getStoreName().equals("Walmart"));
        assertTrue("This store should be Walmart", result.get(1).getStoreName().equals("Walmart"));
        assertTrue("The two walmarts should differ from each other",
                !result.get(0).getStoreAddress().equals(result.get(1).getStoreAddress()));
    }

    @Test
    public void testUpperAndLowerCase() {
        List<Store> result = accessStores.getStoresByName("cOsTcO");
        assertTrue("The store returned should still be costco regardless if the letters are upper case or lowercase", result.get(0).getStoreName().equals("Costco"));
    }

    @Test
    public void testPartialSearch() {
        List<Store> result = accessStores.getStoresByName("wal");
        assertTrue("The proper store should still be returned even if input is incomplete", result.get(0).getStoreName().equals("Walmart"));
    }

}
