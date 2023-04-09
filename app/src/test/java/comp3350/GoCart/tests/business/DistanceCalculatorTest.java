package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;


import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.DistanceCalculator;
import comp3350.GoCart.business.DistanceCalculatorAPI;
import comp3350.GoCart.business.DistanceCalculatorRandom;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.StorePersistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import android.os.health.SystemHealthManager;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculatorTest extends TestCase {
    /*
     Note that we are testing the DistanceCalculators and not the access stores method.
     This is because this is where the getNearestStores methods work is done.
     */

    private DistanceCalculator api;
    private DistanceCalculator stub;
    private AccessStores accessStores;
    private StorePersistence storePersistence;

    public DistanceCalculatorTest() {
        super();
        api = new DistanceCalculatorAPI();
        stub = new DistanceCalculatorRandom();
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

    @Test
    public void testNullLocation() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: null location");

        List<Store> test = accessStores.getStores();
        List<Store> firstCheck = stub.calculateNearestStores(null, test);
        List<Store> secondCheck = api.calculateNearestStores(null, test);

        assertTrue(firstCheck.size() == test.size());
        assertTrue(secondCheck.size() == test.size());

        for(int i = 0 ; i < test.size(); i++) {
            assertTrue("each store should be the same as all the stores in the list",firstCheck.get(i).equals(test.get(i)));
        }

        for(int i = 0 ; i < test.size(); i++) {
            assertTrue("each store should be the same as all the stores in the list",secondCheck.get(i).equals(test.get(i)));
        }
    }

    @Test
    public void testValidData() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: valid data");


        List<Store> test = accessStores.getNearestStores("50 shoreview bay winnipeg");

        List<Store> firstCheck = stub.calculateNearestStores("50 shoreview bay winnipeg", test);
        List<Store> secondCheck = api.calculateNearestStores("50 shoreview bay winnipeg", test);

        assertTrue("sizes should equal: " + firstCheck.size() + " and " + test.size(),firstCheck.size() == test.size());
        assertTrue("sizes should equal: " + secondCheck.size() + " and " + test.size(),secondCheck.size() == test.size());


        //stub version will return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < test.size() - 1; i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }

        //do the same for the api version as it should
        for(int i = 0 ; i < test.size(); i++) {
            assertTrue("should have a randomized distance",secondCheck.get(i).getDistToUser() != 0);
        }
    }

    @Test
    public void testEmptyString() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: empty String");

        List<Store> test = accessStores.getStores();

        //here we want to validate that we get a json exception beacause the api response will not be able to parse the JSON
        try{
            List<Store> secondCheck = api.calculateNearestStores("", test);
        } catch (Exception e) {
            assertTrue("should throw a JSON Exception",e.getClass() == JSONException.class);
        }

        List<Store> firstCheck = stub.calculateNearestStores("", test);



        assertTrue(firstCheck.size() == test.size());

        //stub version will still return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < test.size() - 1; i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }

    }

    @Test
    public void testIncompleteAddress() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: incomplete address");

        List<Store> test = accessStores.getStores();

        //here we want to validate that we get a json exception beacause the api response will not be able to parse the JSON
        try{
            List<Store> secondCheck = api.calculateNearestStores("50 shorev", test);
        } catch (Exception e) {
            assertTrue("should throw a JSON Exception",e.getClass() == JSONException.class);
        }

        List<Store> firstCheck = stub.calculateNearestStores("50 shorev", test);



        assertTrue("sizes should be equal. They are: " + firstCheck.size() + " and " + test.size(),firstCheck.size() == test.size());

        //stub version will still return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < test.size() - 1; i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }
    }

    @Test
    public void testEmptyList() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: Empty List");


        List<Store> firstCheck = stub.calculateNearestStores("50 shoreview bay", new ArrayList<>());
        List<Store> secondCheck = api.calculateNearestStores("50 shoreview bay", new ArrayList<>());

        //list should be empty
        assertTrue("sizes should equal to 0",firstCheck.size() == 0);
        assertTrue("sizes should equal to 0",secondCheck.size() == 0);
    }

}
