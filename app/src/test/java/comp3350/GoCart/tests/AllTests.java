package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.ShoppingCartTest;
import comp3350.GoCart.tests.business.CheapestStoreTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShoppingCartTest.class,
        CheapestStoreTest.class,
})

public class AllTests {
}