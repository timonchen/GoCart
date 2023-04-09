package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.ShoppingCartTest;
import comp3350.GoCart.tests.business.CheapestStoreTest;
import comp3350.GoCart.tests.objects.ProductTest;
import comp3350.GoCart.tests.objects.StoreTest;
import comp3350.GoCart.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //DietaryRestrictionTest.class,
        //SearchProductsByNameTest.class,
        //DistanceCalculatorTest.class,
        //SearchStoresByNameTest.class,
        GetClosetStoresTest.class,
        //SearchStoreProductTest.class,
        ShoppingCartTest.class,
        CheapestStoreTest.class,
        //AccessOrdersTest.class,
        //AccessOrderLinesTest.class,
        //OrderLineTest.class,
        //OrderTest.class,
        ProductTest.class,
        StoreTest.class,
        UserTest.class,
        //UserAccountsTest.class
})

public class AllTests {
}
