package comp3350.GoCart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShoppingCartTest.class,
        FindProductTest.class,
        FindCheapestOrderTest.class,
        ViewDealsTest.class

})

public class AllAcceptanceTests {
}
