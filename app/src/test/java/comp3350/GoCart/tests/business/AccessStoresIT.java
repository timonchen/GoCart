package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.hsqldb.StorePersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

public class AccessStoresIT {
    private AccessStores accessStores;
    private File tempDB;

    @Before
    public void init() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final List<Store> stores;
        final StorePersistence persistence = new StorePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessStores = new AccessStores(persistence);
    }

    @Test
    public void testGetStores(){
        final List<Store> stores;
        stores = accessStores.getStores();
        assertEquals(6,stores.size());
    }

    @Test
    public void testGetByName(){
        final List<Store> stores;
        stores = accessStores.getStoresByName("Costco");
        assertEquals(2,stores.size());
        for(Store s :stores){
            assertEquals("Costco",s.getStoreName());
        }
    }

    @Test
    public void testGetNearest(){
        final List<Store> stores;
        stores = accessStores.getNearestStores("66 Chancellors Circle");
        double prevDist = 0;
        for(Store s : stores){
            assertTrue("each store is farther then previous", prevDist< s.getDistToUser());
            prevDist = s.getDistToUser();
        }
    }

    @After
    public void terminate() {
        // reset DB
        this.tempDB.delete();
    }

}
