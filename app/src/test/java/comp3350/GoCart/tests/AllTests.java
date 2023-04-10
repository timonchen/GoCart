package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import comp3350.GoCart.tests.business.AccessOrderLinesTest;
import comp3350.GoCart.tests.business.AccessOrdersTest;
import comp3350.GoCart.tests.business.AccessProductsTest;
import comp3350.GoCart.tests.business.AccessStoreProductTest;
import comp3350.GoCart.tests.business.AccessStoresTest;
import comp3350.GoCart.tests.business.DietaryRestrictionTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;
import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.ShoppingCartTest;
import comp3350.GoCart.tests.business.CheapestStoreTest;
import comp3350.GoCart.tests.business.UserAccountsTest;
import comp3350.GoCart.tests.objects.OrderLineTest;
import comp3350.GoCart.tests.objects.OrderTest;
import comp3350.GoCart.tests.objects.ProductTest;
import comp3350.GoCart.tests.objects.StoreTest;
import comp3350.GoCart.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        DietaryRestrictionTest.class,
        AccessProductsTest.class,
        DistanceCalculatorTest.class,
        AccessStoresTest.class,
        GetClosetStoresTest.class,
        AccessStoreProductTest.class,
        ShoppingCartTest.class,
        CheapestStoreTest.class,
        AccessOrdersTest.class,
        AccessOrderLinesTest.class,
        OrderLineTest.class,
        OrderTest.class,
        ProductTest.class,
        StoreTest.class,
        UserTest.class,
        UserAccountsTest.class
})

public class AllTests {
}
