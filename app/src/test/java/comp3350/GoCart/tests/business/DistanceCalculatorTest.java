package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.fail;

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.DistanceCalculator;
import comp3350.GoCart.business.DistanceCalculatorAPI;
import comp3350.GoCart.business.DistanceCalculatorStub;
import comp3350.GoCart.objects.Store;

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
    private List<Store> stores;

    public DistanceCalculatorTest() {
        super();
        api = new DistanceCalculatorAPI();
        stub = new DistanceCalculatorStub();
        accessStores = new AccessStores();
        stores = accessStores.getStores();
    }

    @Test
    public void testNullLocation() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: null location");

        List<Store> firstCheck = stub.calculateNearestStores(null, stores);
        List<Store> secondCheck = api.calculateNearestStores(null, stores);

        assertTrue(firstCheck.size() == stores.size());
        assertTrue(secondCheck.size() == stores.size());

        for(int i = 0 ; i < stores.size(); i++) {
            assertTrue("each store should be the same as all the stores in the list",firstCheck.get(i).equals(stores.get(i)));
        }

        for(int i = 0 ; i < stores.size(); i++) {
            assertTrue("each store should be the same as all the stores in the list",secondCheck.get(i).equals(stores.get(i)));
        }
    }

    @Test
    public void testValidData() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: valid data");

        List<Store> firstCheck = stub.calculateNearestStores("50 shoreview bay", stores);
        List<Store> secondCheck = api.calculateNearestStores("50 shoreview bay", stores);

        assertTrue("sizes should equal",firstCheck.size() == stores.size());
        assertTrue("sizes should equal",secondCheck.size() == stores.size());

        //stub version will return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < stores.size() - 1; i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }

        //do the same for the api version as it should
        for(int i = 0 ; i < stores.size(); i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }
    }

    @Test
    public void testEmptyString() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: empty String");

        //here we want to validate that we get a json exception beacause the api response will not be able to parse the JSON
        try{
            List<Store> secondCheck = api.calculateNearestStores("", stores);
        } catch (Exception e) {
            assertTrue("should throw a JSON Exception",e.getClass() == JSONException.class);
        }

        List<Store> firstCheck = stub.calculateNearestStores("", stores);



        assertTrue(firstCheck.size() == stores.size());

        //stub version will still return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < stores.size() - 1; i++) {
            assertTrue("should have a randomized distance",firstCheck.get(i).getDistToUser() != 0);
        }

    }

    @Test
    public void testIncompleteAddress() throws Exception {
        System.out.println("\nStarting testDistanceCalculator: incomplete address");

        //here we want to validate that we get a json exception beacause the api response will not be able to parse the JSON
        try{
            List<Store> secondCheck = api.calculateNearestStores("50 shorev", stores);
        } catch (Exception e) {
            assertTrue("should throw a JSON Exception",e.getClass() == JSONException.class);
        }

        List<Store> firstCheck = stub.calculateNearestStores("50 shorev", stores);



        assertTrue(firstCheck.size() == stores.size());

        //stub version will still return a randomized distance list so we'll verify that they aren't 0
        for(int i = 0 ; i < stores.size() - 1; i++) {
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
