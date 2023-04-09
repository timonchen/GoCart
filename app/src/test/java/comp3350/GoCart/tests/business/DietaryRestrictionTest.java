package comp3350.GoCart.tests.business;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class DietaryRestrictionTest {
    private AccessProducts accessProducts;
    private ProductPersistence mockProductPersistence;

    public DietaryRestrictionTest() {
        super();
    }

    // Set up the testing environment with mock data
    @Before
    public void initialize() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Banana", false, "produce"));
        productList.add(new Product("2", "Rye Bread", true, "bakery"));
        productList.add(new Product("3", "Whole Wheat Bread", true, "bakery"));
        productList.add(new Product("4", "Lucky Charms", true, "bakery"));
        productList.add(new Product("5", "12 cookies", true, "bakery"));

        mockProductPersistence = mock(ProductPersistence.class);
        accessProducts = new AccessProducts(mockProductPersistence);

        when(mockProductPersistence.getDietaryRestrictedProducts()).thenReturn(productList);
    }

    // Test if the list is not empty
    @Test
    public void testEmptyList() {
        List<Product> testList = accessProducts.getDietaryProducts();

        assertFalse("Should not be empty", testList.isEmpty());

        System.out.println("Finished testEmptyList: empty list");
    }

    // Test dietary restrictions for two products
    @Test
    public void testDietaryRestriction() {
        System.out.println("\nchecking dietary restriction in a product");
        Product p1 = new Product("1", "Banana", false, "produce");
        Product p2 = (new Product("2", "Rye Bread", true, "bakery"));
        assertFalse("The product should have peanut allergy", p1.hasPeanutAllergy());
        assertTrue("The product should not have peanut allergy", p2.hasPeanutAllergy());
        System.out.println("Finished testDietaryRestriction");
    }

    // Test if the list of dietary products is returned correctly
    @Test
    public void testShowDietaryProducts() {
        System.out.println("\nGet dietaryRestricted products");
        List<Product> testList = accessProducts.getDietaryProducts();
        System.out.println(testList.toString());
        assertTrue("Should return a list of the same size", testList.size() == 5);

        System.out.println("Finished testShowDietaryProducts");
    }

    // Test if the list is filtered correctly for products with peanut allergy
    @Test
    public void testFilterDietaryProducts() {
        System.out.println("\nFiltering dietaryRestricted products");
        List<Product> testList = accessProducts.getDietaryProducts();
        long count = testList.stream().filter(Product::hasPeanutAllergy).count();
        assertTrue("Should return the correct number of products with peanut allergy", count == 4);

        System.out.println("Finished testFilterDietaryProducts");
    }

}