package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.DietaryRestrictionTest;
import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.SearchProductsByNameTest;
import comp3350.GoCart.tests.business.CalculateCheapestStoreTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;
import comp3350.GoCart.tests.business.SearchStoresByNameTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculateCheapestStoreTest.class,
        DietaryRestrictionTest.class,
        SearchProductsByNameTest.class,
        DistanceCalculatorTest.class,
<<<<<<< app/src/test/java/comp3350/GoCart/tests/AllTests.java
        SearchStoresByNameTest.class
=======

        GetClosetStoresTest.class
>>>>>>> app/src/test/java/comp3350/GoCart/tests/AllTests.java
})

public class AllTests {
}