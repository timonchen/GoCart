package comp3350.GoCart.tests.business;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.UserPersistence;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class DietaryRestrictionTest extends TestCase {
    boolean isEmpty;
    private AccessProducts accessProducts;
    private ProductPersistence productPersistence;

    public DietaryRestrictionTest() {
        super();
        productPersistence = mock(ProductPersistence.class);
        accessProducts = new AccessProducts(productPersistence);
    }

    @Test
    public void testEmptyList() {

        //add some products to the list
        List<Product> prods = new ArrayList<>();
        Product p1 = new Product("1", "Banana", true, "produce");
        Product p2 = new Product("2", "Rye Bread", true, "bakery");
        prods.add(p1);
        prods.add(p2);

        //mock our productPersistent calls
        when(productPersistence.getDietaryRestrictedProducts()).thenReturn(prods);
        List<Product> testList = accessProducts.getDietaryProducts();

        assertFalse("Should not be empty", testList.isEmpty());

        System.out.println("Finished testEmptyList: empty list");
    }

    @Test
    public void testDietaryRestriction() {
        System.out.println("\nchecking dietary restriction in a product");
        Product p1 = new Product("1", "Banana", false, "produce");
        Product p2 = (new Product("2", "Rye Bread", true, "bakery"));
        assertFalse("The product should have peanut allergy", p1.hasPeanutAllergy());
        assertTrue("The product should not have peanut allergy", p2.hasPeanutAllergy());
        System.out.println("Finished testDietaryRestriction");

    }

    @Test
    public void testShowDietaryProducts() {
        System.out.println("\nGet dietaryRestricted products");
        List<Product> list = new ArrayList<>();
        list.add(new Product( "6849","Rye Bread", true, "bakery"));
        list.add(new Product( "6917","Whole Wheat Bread", true, "bakery"));
        list.add(new Product( "3818","Lucky Charms",true, "bakery"));
        list.add(new Product( "1958","12 cookies", true, "bakery"));

        when(productPersistence.getDietaryRestrictedProducts()).thenReturn(list);
        List<Product> testList = accessProducts.getDietaryProducts();
        assertTrue("Should return a list of the same size", list.size() == testList.size());



        System.out.println("Finished testShowDietaryProducts");

   }

}