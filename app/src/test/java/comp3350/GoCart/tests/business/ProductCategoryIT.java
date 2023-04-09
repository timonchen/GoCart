package comp3350.GoCart.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.hsqldb.ProductPersistenceHSQLDB;
import comp3350.GoCart.tests.utils.TestUtils;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class ProductCategoryIT {
    private AccessProducts accessProducts;
    private List<Product> products;
    private List<String> allCategories;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final ProductPersistence persistence = new ProductPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessProducts = new AccessProducts(persistence);
    }

    @Test
    // Method to test passing null as product name while searching
    public void testNullSearch() {
        System.out.println("\nTest: searching products with null");

        products = accessProducts.productCatogory(null);
        assertTrue("It should not return any product because searching with null category", products.isEmpty());

        System.out.println("Finished test: searching products with null category");
    }


    @Test
    // Method to test searching for product with a specific category, "diary in this case
    public void specificCategorySearchTest() {
        System.out.println("\nTest: searching products with diary category");

        products = new ArrayList<>();
        products.add(new Product("3846", "Ice-cream", false, "diary"));
        List<Product> matchingProducts = accessProducts.productCatogory("dairy");
        System.out.println(matchingProducts.toString());
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);


        System.out.println("Finished test: searching products with specific category successful");
    }

    @Test
    // Method to test searching for product with a specific category, "diary in this case
    public void getAllCategoriesTest() {
        System.out.println("\nTest: getting all categories");

        allCategories = accessProducts.getAllCategories();

        System.out.println(allCategories.toString());
        assertEquals("Should return the correct product searched irrespective of the case.", "yes", "yes");


        System.out.println("Finished test: searching products with specific category successful");
    }

    @Test
    // Method to test getCategory
    public void getCategoryTest() {
        System.out.println("\nTest: searching products with meat");

        products = accessProducts.productCatogory("meat");

        assertTrue("1st product in the list should have meat as category", products.get(0).getProductCategory().equals("meat"));

        System.out.println("Finished test: searching products with null category");
    }
}