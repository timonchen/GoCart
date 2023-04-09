package comp3350.GoCart.systemTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.objects.User;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShoppingCartTest.class,
        FindProductTest.class,
        FindCheapestOrderTest.class,
        ViewDealsTest.class,
        DietaryRestrictionTest.class,
        FindClosestStore.class,
        SearchProductByCategoryTest.class,
        UserAccountTest.class

})

public class AllAcceptanceTests {
}
