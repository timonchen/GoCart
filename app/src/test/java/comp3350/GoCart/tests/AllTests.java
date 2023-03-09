package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.CheapestStoreTest;
import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;
import comp3350.GoCart.tests.business.ShoppingCartTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        DistanceCalculatorTest.class,

        GetClosetStoresTest.class,
        ShoppingCartTest.class,
        CheapestStoreTest.class



})

public class AllTests {
}