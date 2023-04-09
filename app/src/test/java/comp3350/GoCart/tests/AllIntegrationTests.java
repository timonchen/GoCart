package comp3350.GoCart.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.GoCart.tests.business.AccessProductsIT;
import comp3350.GoCart.tests.business.AccessStoreProductsIT;
import comp3350.GoCart.tests.business.AccessStoresIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessStoresIT.class,
        AccessStoreProductsIT.class,
        AccessProductsIT.class
})

public class AllIntegrationTests {
}
