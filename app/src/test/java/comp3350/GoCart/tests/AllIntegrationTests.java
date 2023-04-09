package comp3350.GoCart.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import comp3350.GoCart.tests.business.AccessOrderLinesIT;
import comp3350.GoCart.tests.business.AccessOrdersIT;
import comp3350.GoCart.tests.business.AccessProductsIT;
import comp3350.GoCart.tests.business.AccessStoreProductsIT;
import comp3350.GoCart.tests.business.AccessStoresIT;
import comp3350.GoCart.tests.business.AccessUsersIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessStoreProductsIT.class,
        AccessOrderLinesIT.class,
        AccessOrdersIT.class,
        AccessProductsIT.class,
        AccessStoresIT.class,
        AccessUsersIT.class,

})

public class AllIntegrationTests {
}
