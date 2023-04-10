package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;


import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.StorePersistence;


public class AccessStoresTest {
    private AccessStores accessStores;
    private StorePersistence storePersistence;


    @Before
    public void SearchStoresByNameTest() {
        storePersistence = mock(StorePersistence.class);
        accessStores = new AccessStores(storePersistence);

        List<Store> storeList = new ArrayList<>();
        //stores to return for mock
        storeList.add(new Store("1","Walmart", "35 Lakewood Blvd Winnipeg"));
        storeList.add(new Store("2","Walmart", "1576 Regent Ave Winnipeg"));
        storeList.add(new Store("3","Costco", "1499 Regent Ave W Winnipeg"));
        storeList.add(new Store("4","Costco", "2365 McGillivray Blvd Winnipeg"));
        storeList.add(new Store("5","Safeway", "2025 Corydon Ave Winnipeg"));
        storeList.add(new Store("6","Safeway", "655 Osborne Winnipeg"));

        when(storePersistence.getAllStores()).thenReturn(storeList);
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

    @Test
    public void testGetStoreByID() {
        Store result = accessStores.getStoreByID("1");
        assertTrue("Store id returned should be 1", result.getStoreID().equals("1"));
    }

}
