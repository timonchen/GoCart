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


/*
* Note that the current implentation of AccessStores uses the API distance calculator so these tests reflect that
*/
public class GetClosetStoresTest {

    private AccessStores accessStores;
    private StorePersistence storePersistence;

    public GetClosetStoresTest() {
        super();
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
    public void testInvalidAddress() {
        System.out.println("\nStarting GetClosestStores: Empty address");

        List<Store> nearestStores = accessStores.getNearestStores("501 fa");

        assertTrue("Invalid address should return empty list", nearestStores.size() == 0);
    }

    @Test
    public void testValidAddress() throws Exception {
        System.out.println("\nStarting GetClosestStores: valid address");


        List<Store> nearestStores = accessStores.getNearestStores("50 shoreview bay");

        assertTrue("valid address should return a different list of the stores", !nearestStores.equals(accessStores.getStores()));

        for(int i = 0; i < nearestStores.size()-1; i++) {
            assertTrue("each store should be sorted from smallest to largest", nearestStores.get(i).compareTo(nearestStores.get(i+1)) <= 0.0);
        }


        nearestStores = accessStores.getNearestStores("139 tuxedo ave");

        assertTrue("valid address should return a different list of the stores", !nearestStores.equals(accessStores.getStores()));

        for(int i = 0; i < nearestStores.size()-1; i++) {
            assertTrue("each store should be sorted from smallest to largest", nearestStores.get(i).compareTo(nearestStores.get(i+1)) <= 0.0);
        }

        nearestStores = accessStores.getNearestStores("66 66 Chancellors Cir,");

        assertTrue("valid address should return a different list of the stores", !nearestStores.equals(accessStores.getStores()));

        for(int i = 0; i < nearestStores.size()-1; i++) {
            assertTrue("each store should be sorted from smallest to largest", nearestStores.get(i).compareTo(nearestStores.get(i+1)) <= 0.0);
        }
    }

    @Test
    public void testEmptyAddress() {
        System.out.println("\nStarting GetClosestStores: Empty address");

        List<Store> nearestStores = accessStores.getNearestStores("");

        assertTrue("Invalid address should return empty list", nearestStores.size() == 0);
    }


}
