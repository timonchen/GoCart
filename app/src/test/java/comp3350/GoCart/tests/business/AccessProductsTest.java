package comp3350.GoCart.tests.business;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;


public class AccessProductsTest {
    private AccessProducts accessProducts;
    private ProductPersistence mockProductPersistence;
    private List<Product> products;

    @Before
    public void init()
    {
        mockProductPersistence = mock(ProductPersistence.class);
        accessProducts = new AccessProducts(mockProductPersistence);






    }

    @Test
    // Method to test passing null as product name while searching
    public void testNullSearch()
    {
        when(accessProducts.searchProductsByName(null)).thenReturn(new ArrayList<>());

        products = accessProducts.searchProductsByName(null);

        assertTrue("It should not return any product because searching with null", products.isEmpty());

        System.out.println("Finished test: searching products with null");
    }

    @Test
    // Method to test passing empty String as product name while searching
    public void testEmptyStringSearch()
    {

        System.out.println("\nTest: searching products with empty string");
        List<Product> products = new ArrayList<>();
        products.add(new Product("4521", "Banana", false, "produce"));
        when(accessProducts.searchProductsByName("")).thenReturn(products);
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
        products.add(new Product("4521","Banana",false,"produce"));
        when(accessProducts.searchProductsByName("banana")).thenReturn(products);
        List<Product> matchingProducts = accessProducts.searchProductsByName("banana");
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);


        products = new ArrayList<>();
        products.add(new Product( "6849","Rye Bread", true,"Bakery"));
        when(accessProducts.searchProductsByName("RYE BREAD")).thenReturn(products);


        matchingProducts = accessProducts.searchProductsByName("RYE BREAD");
        assertEquals("Should return the correct product searched irrespective of the case.", products, matchingProducts);

        products = new ArrayList<>();
        products.add(new Product( "6917","Whole Wheat Bread", true,"Bakery"));
        when(accessProducts.searchProductsByName("wHOLE wHEAT bREAD")).thenReturn(products);
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

        products.add(new Product("4521", "Banana", false, "produce"));
        products.add(new Product("6849", "Rye Bread", true, "bakery"));
        products.add(new Product("6917", "Whole Wheat Bread", true, "bakery"));
        products.add(new Product("3818", "Lucky Charms", true, "bakery"));
        products.add(new Product("3984", "toilet paper", false, "house hold"));
        products.add(new Product("3846", "Ice-cream", false, "dairy"));
        when(accessProducts.searchProductsByName("a")).thenReturn(products);

        List<Product> matchingProducts = accessProducts.searchProductsByName("a");
        assertTrue("Should be same size", products.size() == matchingProducts.size());
        for(int i = 0; i < products.size(); i++) {
            assertTrue("all products should be the same", products.get(i).equals(matchingProducts.get(i)));
        }


        System.out.println("Finished test: searching products with very few characters in string");
    }

    @Test
    // Method to test searching products with similar words in their name
    public void testCloselyMatchingProductsSearch()
    {
        System.out.println("\nTest: searching products with very close names");

        products = new ArrayList<>();

        products.add(new Product("6849", "Rye Bread", true, "bakery"));
        products.add(new Product("6917", "Whole Wheat Bread", true, "bakery"));
        when(accessProducts.searchProductsByName("bread")).thenReturn(products);


        List<Product> matchingProducts = accessProducts.searchProductsByName("bread");

        assertTrue("Should be same size", products.size() == matchingProducts.size());
        for(int i = 0; i < products.size(); i++) {
            assertTrue("all products should be the same", products.get(i).equals(matchingProducts.get(i)));
        }

        System.out.println("Finished test: searching products with very close names");
    }

    @Test
    // Method to test searching products with similar words in their name
    public void testWhiteSpaceSearch()
    {
        System.out.println("\nTest: searching products with whitespace");

        products = new ArrayList<>();

        products.add(new Product("4521", "Banana", false, "produce"));
        products.add(new Product("6849", "Rye Bread", true, "bakery"));
        products.add(new Product("6917", "Whole Wheat Bread", true, "bakery"));
        products.add(new Product("3818", "Lucky Charms", true, "bakery"));
        products.add(new Product("3984", "toilet paper", false, "house hold"));
        products.add(new Product("3846", "Ice-cream", false, "dairy"));
        when(accessProducts.searchProductsByName(" ")).thenReturn(products);
        List<Product> matchingProducts = accessProducts.searchProductsByName(" ");
        assertTrue("Should be same size", products.size() == matchingProducts.size());
        for(int i = 0; i < products.size(); i++) {
            assertTrue("all products should be the same", products.get(i).equals(matchingProducts.get(i)));
        }

        System.out.println("Finished test: searching products with whitespace");
    }

}
