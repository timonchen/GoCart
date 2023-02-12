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
        product = new Product("testNullProduct", new BigDecimal(12.131233), false);
        System.out.println("\nStarting testNullProduct");
        assertNotNull("Product should not be null", product);

    }

    @Test
    public void testProductName() {
        product = new Product("testProduct", new BigDecimal(12.131233), false);
        System.out.println("\nStarting testProductName");
        assertTrue("Product name should be same as the one we are testing", "testProduct".equals(product.getProductName()));
    }

    @Test
    public void testProductPrice() {
        product = new Product("testProductPrice", new BigDecimal(12.131233), false);
        System.out.println("\nStarting testNullProduct");
        assertTrue("Product price should be same as the one we are testing", new BigDecimal(12.131233).equals(product.getProductPrice()));

    }

    @Test
    public void testProductAllergy() {
        product = new Product("testProduct", new BigDecimal(12.131233), false);

        System.out.println("\nStarting testProductAllergy");
        assertFalse("Product price should not have dietary restrictions", (product.hasPeanutAllergy()));
    }

    @Test
    public void testProductEquals() {
        product = new Product("testProduct", new BigDecimal(12.131233), false);
        System.out.println("\nStarting testProductEquals");
        Product product2 = new Product("testProduct", new BigDecimal(12.131233), false);
        assertTrue("Both products should be equal", product.equals(product2));

    }

}