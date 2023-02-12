package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;



public class DietaryRestrictionTest extends TestCase {
    boolean isEmpty;
    private List<Product> list;
    private AccessProducts se;

    @Test
    public void testEmptyList() {
        System.out.println("\nGet products: empty list");


        list = new ArrayList<>();
        isEmpty = true;
        assertTrue(isEmpty == (list.isEmpty()));

        System.out.println("Finished testCalculateGPA: empty list");
    }

    @Test
    public void testDietaryRestriction(){
        System.out.println("\nchecking dietary restriction in a product");
        Product p1 =new Product("Banana", new BigDecimal("1.05"),false);
        Product p2 = (new Product("Rye Bread", new BigDecimal("1.45"),true));
        assertFalse(p1.hasPeanutAllergy());
        assertTrue(p2.hasPeanutAllergy());

    }

    @Test
    public void testShowDietaryRestrictedProducts(){
        System.out.println("\nGet dietaryRestricted products");
        list = new ArrayList<>();
        list.add(new Product("Banana", new BigDecimal("1.05"),false));
        list.add(new Product("Beef Jerkey", new BigDecimal("9.99"),false));
        list.add(new Product("toilet paper", new BigDecimal("15.99"),false));

        ProductPersistenceStub test = new ProductPersistenceStub();
        List<Product> testList=test.getDietaryRestrictedProducts();
        System.out.println(testList.toString());
        assertTrue( testList.containsAll(list)&&(list).containsAll(testList));

    }
    

}