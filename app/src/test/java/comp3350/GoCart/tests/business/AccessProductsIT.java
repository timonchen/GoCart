package comp3350.GoCart.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.hsqldb.ProductPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

public class AccessProductsIT {
    private AccessProducts accessProducts;
    private File tempDB;

    @Before
    public void init() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final ProductPersistence persistence = new ProductPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessProducts = new AccessProducts(persistence);
    }

    @Test
    public void testDietaryProducts(){
        final List<Product> products;
        products = accessProducts.getDietaryProducts();
        assertEquals(4,products.size());
        for (Product p: products){
            assertEquals(false,p.hasPeanutAllergy());
        }
    }

    @Test
    public void testSearchByName(){
        final Product product;
        product = accessProducts.searchProductsByName("Banana").get(0);
        assertTrue("Banana".equals(product.getProductName()));

    }

    @After
    public void terminate() {
        // reset DB
        this.tempDB.delete();
    }


}
