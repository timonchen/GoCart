package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;

public class SearchProductsByNameTest extends TestCase
{
    private AccessProducts accessProducts;
    private List<Product> products;

    public SearchProductsByNameTest()
    {
        super();
        accessProducts = new AccessProducts();
    }

    @Test
    public void testNullSearch()
    {
        System.out.println("\nTest: searching products with null");

        products = accessProducts.searchProductsByName(null);
        assertTrue(products.isEmpty());

        System.out.println("Finished test: searching products with null");
    }

    @Test
    public void testEmptyStringSearch()
    {
        System.out.println("\nTest: searching products with empty string");

        List<Product> matchingProducts = accessProducts.searchProductsByName("");
        assertFalse(matchingProducts.isEmpty());    //returns all the products (no filter)

        System.out.println("Finished test: searching products with empty string");
    }

    @Test
    public void testCaseMismatchInSearch()
    {
        System.out.println("\nTest: searching products with case mismatch");

        products = new ArrayList<>();
        products.add(new Product("Banana", new BigDecimal("1.05"),false));
        List<Product> matchingProducts = accessProducts.searchProductsByName("banana");
        assertEquals(products, matchingProducts);

        products = new ArrayList<>();
        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        matchingProducts = accessProducts.searchProductsByName("RYE BREAD");
        assertEquals(products, matchingProducts);

        products = new ArrayList<>();
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));
        matchingProducts = accessProducts.searchProductsByName("wHOLE wHEAT bREAD");
        assertEquals(products, matchingProducts);

        System.out.println("Finished test: searching products with case mismatch");
    }

    @Test
    public void testVeryFewCharactersSearch()
    {
        System.out.println("\nTest: searching products with very few characters in string");

        products = new ArrayList<>();

        products.add(new Product("Banana", new BigDecimal("1.05"),false));
        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Lucky Charms", new BigDecimal("3.99"),true));
        products.add(new Product("toilet paper", new BigDecimal("15.99"),false));

        List<Product> matchingProducts = accessProducts.searchProductsByName("a");
        assertEquals(products, matchingProducts);

        System.out.println("Finished test: searching products with very few characters in string");
    }

    @Test
    public void testCloselyMatchingProductsSearch()
    {
        System.out.println("\nTest: searching products with very close names");

        products = new ArrayList<>();

        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Whole Wheat Bread", new BigDecimal("1.45"),true));

        List<Product> matchingProducts = accessProducts.searchProductsByName("bread");
        assertEquals(products, matchingProducts);

        System.out.println("Finished test: searching products with very close names");
    }

    @Test
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
        assertEquals(products, matchingProducts);

        System.out.println("Finished test: searching products with whitespace");
    }

    @Test
    public void testSpecialCharactersSearch() {
        System.out.println("\nTest: searching products with special characters in String");

        List<Product> matchingProducts = accessProducts.searchProductsByName(",@#/?><.;[]{}\"'");
        assertTrue(matchingProducts.isEmpty());

        System.out.println("Finished test: searching products with special characters in String");
    }
}
