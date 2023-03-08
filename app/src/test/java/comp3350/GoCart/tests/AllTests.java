package comp3350.GoCart.tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.GetClosetStoresTest;
import comp3350.GoCart.tests.business.DistanceCalculatorTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({


        //DistanceCalculatorTest.class,

        GetClosetStoresTest.class


})

public class AllTests {
}