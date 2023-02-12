package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.DietaryRestrictionTest;
import comp3350.GoCart.tests.business.SearchProductsByNameTest;
import comp3350.GoCart.tests.business.CalculateCheapestStoreTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculateCheapestStoreTest.class,
        DietaryRestrictionTest.class,
        SearchProductsByNameTest.class,
        DistanceCalculatorTest.class
})

public class AllTests {
}