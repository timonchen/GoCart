package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;

public class SearchProductsByNameTest extends TestCase
{/*
    private AccessProducts accessProducts;
    private List<Product> products;

    public SearchProductsByNameTest()
    {
        super();
        accessProducts = new AccessProducts();
    }

    @Test
    // Method to test passing null as product name while searching
    public void testNullSearch()
    {
        System.out.println("\nTest: searching products with null");

        products = accessProducts.searchProductsByName(null);
        assertTrue("It should not return any product because searching with null", products.isEmpty());

        System.out.println("Finished test: searching products with null");
    }

    @Test
    // Method to test passing empty String as product name while searching
    public void testEmptyStringSearch()
    {
        System.out.println("\nTest: searching products with empty string");

        List<Product> matchingProducts = accessProducts.searchProductsByName("");
        assertFalse("It should return all the products because no filter was put.", matchingProducts.isEmpty());    //returns all the products (no filter)

        System.out.println("Finished test: searching products with empty string");
    }

    @Test
    // Method to test searching with different cases for letters for product name while searching
    public void testCaseMismatchInSearch()
    {
        System.out.println("\nTest: searching products with case mismatch");

        products = new ArrayList<>();
        products.add(new Product("Banana", new BigDecimal("1.05"),false));
        List<Product> matchingProducts = accessProducts.searchProductsByName("banana");
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);

        products = new ArrayList<>();
        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        matchingProducts = accessProducts.searchProductsByName("RYE BREAD");
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);

        products = new ArrayList<>();
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));
        matchingProducts = accessProducts.searchProductsByName("wHOLE wHEAT bREAD");
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);

        System.out.println("Finished test: searching products with case mismatch");
    }

    @Test
    // Method to test searching with very few characters for product name while searching
    public void testVeryFewCharactersSearch()
    {
        System.out.println("\nTest: searching products with very few characters in string");

        products = new ArrayList<>();

        products.add(new Product("Banana", new BigDecimal("1.05"),false));
        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Lucky Charms", new BigDecimal("3.99"),true));
        products.add(new Product("toilet paper", new BigDecimal("15.99"),false));
        products.add(new Product("Ice-cream", new BigDecimal("5.99"), false));

        List<Product> matchingProducts = accessProducts.searchProductsByName("a");
        assertEquals("Should return all the products that contain the letter searched.", products, matchingProducts);

        System.out.println("Finished test: searching products with very few characters in string");
    }

    @Test
    // Method to test searching products with similar words in their name
    public void testCloselyMatchingProductsSearch()
    {
        System.out.println("\nTest: searching products with very close names");

        products = new ArrayList<>();

        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));

        List<Product> matchingProducts = accessProducts.searchProductsByName("bread");
        assertEquals("Should return all the products containing the searched characters.", products, matchingProducts);

        System.out.println("Finished test: searching products with very close names");
    }

    @Test
    // Method to test searching products with similar words in their name
    public void testWhiteSpaceSearch()
    {
        System.out.println("\nTest: searching products with whitespace");

        products = new ArrayList<>();

        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Lucky Charms", new BigDecimal("3.99"),true));
        products.add(new Product("Beef Jerkey", new BigDecimal("9.99"),false));
        products.add(new Product("12 cookies", new BigDecimal("12.99"),true));
        products.add(new Product("toilet paper", new BigDecimal("15.99"),false));

        List<Product> matchingProducts = accessProducts.searchProductsByName(" ");
        assertEquals("Should return all the products which have whitespace in their name (name containing more than one word).", products, matchingProducts);

        System.out.println("Finished test: searching products with whitespace");
    }

    @Test
    // Method to test searching products with special characters in their name
    public void testSpecialCharactersSearch() {
        System.out.println("\nTest: searching products with special characters in String");

        List<Product> matchingProducts = accessProducts.searchProductsByName(",@#/?><.;[]{}\"'");
        assertTrue("Should return nothing because no product contains these characters.", matchingProducts.isEmpty());

        products = new ArrayList<>();
        products.add(new Product("Ice-cream", new BigDecimal("5.99"), false));

        matchingProducts = accessProducts.searchProductsByName("Ice-cream");
        assertEquals("Should return the correct product name searched with hyphen.", matchingProducts, products);

        System.out.println("Finished test: searching products with special characters in String");
    }
*/}
