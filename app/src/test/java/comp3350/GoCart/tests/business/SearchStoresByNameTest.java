package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import comp3350.GoCart.business.GetByName;
import comp3350.GoCart.objects.Store;
import java.util.List;
import java.util.ArrayList;

public class SearchStoresByNameTest extends TestCase {
    private List<Store> stores;

    public SearchStoresByNameTest() {
        super();
    }

    // Search for a store that does not exist
    @Test
    public void testNoStoresFound() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Costco", "321 Vernon Road Winnipeg"));
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Safeway", "21 Peltier Avenue Winnipeg"));
        stores.add(new Store("Walmart", "57 Lily Street Winnipeg"));

        List<Store> result = GetByName.stores("SuperStore", stores);
        assertTrue("List should be empty", result.isEmpty());
    }

    @Test
    public void testBlankStore() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Costco", "321 Vernon Road Winnipeg"));
        stores.add(new Store("Safeway", "21 Peltier Avenue Winnipeg"));

        List<Store> result = GetByName.stores("", stores);
        assertTrue("List should be empty", result.isEmpty());
    }


    // Test one store found
    @Test
    public void testOneStoreFound() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Costco", "321 Vernon Road Winnipeg"));
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Safeway", "21 Peltier Avenue Winnipeg"));
        stores.add(new Store("Walmart", "57 Lily Street Winnipeg"));

        List<Store> result = GetByName.stores("Safeway", stores);
        assertTrue("List should have only Safeway", result.get(0).getStoreName().equals("Safeway"));
        assertTrue("There should only be one element in the results", result.size() == 1);
    }

    // Test multiple stores found
    @Test
    public void testMultipleStoresFound() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Costco", "321 Vernon Road Winnipeg"));
        stores.add(new Store("Walmart", "11 Berkshire Bay Winnipeg"));
        stores.add(new Store("Safeway", "21 Peltier Avenue Winnipeg"));
        stores.add(new Store("Walmart", "57 Lily Street Winnipeg"));

        List<Store> result = GetByName.stores("Walmart", stores);
        assertTrue("There should be 3 stores with the name Walmart", result.size() == 3);
        assertTrue("This store should be Walmart", result.get(0).getStoreName().equals("Walmart"));
        assertTrue("This store should be Walmart", result.get(1).getStoreName().equals("Walmart"));
        assertTrue("This store should be Walmart", result.get(2).getStoreName().equals("Walmart"));
        assertTrue("The three walmarts should differ from each other",
                !result.get(0).getStoreAddress().equals(result.get(1).getStoreAddress()) &&
                        !result.get(0).getStoreAddress().equals(result.get(2).getStoreAddress()) &&
                        !result.get(1).getStoreAddress().equals(result.get(2).getStoreAddress()));
    }

    @Test
    public void testUpperAndLowerCase() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));
        stores.add(new Store("Costco", "321 Vernon Road Winnipeg"));
        stores.add(new Store("Walmart", "11 Berkshire Bay Winnipeg"));
        stores.add(new Store("Safeway", "21 Peltier Avenue Winnipeg"));

        List<Store> result = GetByName.stores("cOsTcO", stores);
        assertTrue("The store returned should still be costco reguardless if the letters are upper case or lowercase", result.get(0).getStoreName().equals("Costco"));
    }

    @Test
    public void testPartialSearch() {
        stores = new ArrayList<Store>();
        stores.add(new Store("Walmart", "123 Vista Avenue Winnipeg"));

        List<Store> result = GetByName.stores("wal", stores);
        assertTrue("The proper store should still be returned even if input is incomplete", result.get(0).getStoreName().equals("Walmart"));
    }

}
