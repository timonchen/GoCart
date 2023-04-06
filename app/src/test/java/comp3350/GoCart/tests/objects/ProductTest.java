package comp3350.GoCart.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ProductTest {
    private Product product;

    @Test
    public void testNullProduct() {
        product = new Product("testNullProduct", "name", false, null);
        System.out.println("\nStarting testNullProduct");

        assertNotNull("Product should not be null", product);

    }

    @Test
    public void testProductName() {
        product = new Product("testProduct", "name", false, null);

        System.out.println("\nStarting testProductName");

        assertTrue("Product name should be same as the one we are testing", "name".equals(product.getProductName()));
    }

    @Test
    public void testProductID() {
        product = new Product("testProduct", "name", false,null);

        System.out.println("\nStarting testProductID");

        assertTrue("Product name should be same as the one we are testing", "testProduct".equals(product.getProductID()));
    }


    @Test
    public void testProductAllergy() {
        product = new Product("testProduct", "name", false,null);

        System.out.println("\nStarting testProductAllergy");
        assertFalse("Product price should not have dietary restrictions", (product.hasPeanutAllergy()));
    }

    @Test
    public void testProductEquals() {
        product = new Product("testProduct", "name", false, null);
        System.out.println("\nStarting testProductEquals");
        Product product2 = new Product("testProduct", "name", false, null);
        assertTrue("Both products should be equal", product.equals(product2));

    }

}