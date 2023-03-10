package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.DietaryRestrictionTest;
import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.SearchProductsByNameTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;
import comp3350.GoCart.tests.business.SearchStoreProductTest;
import comp3350.GoCart.tests.business.SearchStoresByNameTest;
import comp3350.GoCart.tests.business.ShoppingCartTest;
import comp3350.GoCart.tests.business.CheapestStoreTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       DietaryRestrictionTest.class,
       SearchProductsByNameTest.class,
        DistanceCalculatorTest.class,
        SearchStoresByNameTest.class,
        GetClosetStoresTest.class,
        SearchStoreProductTest.class,
        ShoppingCartTest.class,
        CheapestStoreTest.class
})

public class AllTests {
}