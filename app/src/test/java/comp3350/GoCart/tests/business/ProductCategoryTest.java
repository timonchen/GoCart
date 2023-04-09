package comp3350.GoCart.tests.business;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class ProductCategoryTest {
    private AccessProducts accessProducts;
    private ProductPersistence mockProductPersistence;

    // Initialize mock objects and setup the behavior for the mockProductPersistence
    @Before
    public void initialize() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("4521", "Banana", false, "produce"));
        productList.add(new Product("6849", "Rye Bread", true, "bakery"));
        productList.add(new Product("6917", "Whole Wheat Bread", true, "bakery"));
        productList.add(new Product("3818", "Lucky Charms", true, "bakery"));
        productList.add(new Product("1953", "Beef Jerkey", false, "meat"));
        productList.add(new Product("1958", "12 cookies", true, "bakery"));
        productList.add(new Product("3984", "toilet paper", false, "house hold"));
        productList.add(new Product("3846", "Ice-cream", false, "dairy"));

        mockProductPersistence = mock(ProductPersistence.class);
        accessProducts = new AccessProducts(mockProductPersistence);

        when(mockProductPersistence.productCategory("dairy")).thenReturn(filterByCategory(productList, "dairy"));
        when(mockProductPersistence.productCategory("meat")).thenReturn(filterByCategory(productList, "meat"));
    }

    // Helper function to filter products by category
    private List<Product> filterByCategory(List<Product> products, String category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // Test searching for products within a specific category (dairy)
    @Test
    public void testSpecificCategorySearch() {
        System.out.println("\nTest: searching products with dairy category");

        List<Product> dairyProducts = accessProducts.productCatogory("dairy");
        assertEquals("Size of dairy products should be 1", 1, dairyProducts.size());
        assertEquals("Should return the correct product searched", "Ice-cream", dairyProducts.get(0).getProductName());

        System.out.println("Finished test: searching products with specific category successful");
    }

    // Test searching for products within a specific category (meat)
    @Test
    public void testGetCategory() {
        System.out.println("\nTest: searching products with meat category");

        List<Product> meatProducts = accessProducts.productCatogory("meat");
        assertEquals("Size of meat products should be 1", 1, meatProducts.size());
        assertEquals("1st product in the list should have meat as category", "meat", meatProducts.get(0).getProductCategory());

        System.out.println("Finished test: searching products with specific category successful");
    }

    // Test searching for products with a null category
    @Test
    public void testNullCategorySearch() {
        System.out.println("\nTest: searching products with null category");

        List<Product> products = accessProducts.productCatogory(null);
        assertTrue("It should not return any product because searching with null category", products.isEmpty());

        System.out.println("Finished test: searching products with null category");
    }

    // Test searching for products with an invalid category
    @Test
    public void testInvalidCategorySearch() {
        System.out.println("\nTest: searching products with invalid category");

        List<Product> products = accessProducts.productCatogory("nonexistent_category");
        assertTrue("It should not return any product because searching with invalid category", products.isEmpty());

        System.out.println("Finished test: searching products with invalid category");
    }

}
