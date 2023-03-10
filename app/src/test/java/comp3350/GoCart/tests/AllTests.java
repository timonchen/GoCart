package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.tests.business.AccessOrderLinesTest;
import comp3350.GoCart.tests.business.AccessOrdersTest;
import comp3350.GoCart.tests.business.DietaryRestrictionTest;
import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.SearchProductsByNameTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;
import comp3350.GoCart.tests.business.SearchStoreProductTest;
import comp3350.GoCart.tests.business.SearchStoresByNameTest;
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
        SearchProductsByNameTest.class,
        DistanceCalculatorTest.class,
        SearchStoresByNameTest.class,
        GetClosetStoresTest.class,
        SearchStoreProductTest.class,
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