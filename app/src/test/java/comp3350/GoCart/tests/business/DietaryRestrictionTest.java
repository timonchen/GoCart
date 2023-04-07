package comp3350.GoCart.tests.business;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.UserPersistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class DietaryRestrictionTest extends TestCase {
    boolean isEmpty;
    private List<Product> list;
    private AccessProducts accessProducts;

    public DietaryRestrictionTest() {
        super();
        accessProducts = new AccessProducts(new ProductPersistenceStub());
    }

    @Test
    public void testEmptyList() {
        List<Product> testList = accessProducts.getDietaryProducts();

        assertFalse("Should not be empty", testList.isEmpty());

        System.out.println("Finished testEmptyList: empty list");
    }

    @Test
    public void testDietaryRestriction() {
        System.out.println("\nchecking dietary restriction in a product");
        Product p1 = new Product("1", "Banana", false);
        Product p2 = (new Product("2", "Rye Bread", true));
        assertFalse("The product should have peanut allergy", p1.hasPeanutAllergy());
        assertTrue("The product should not have peanut allergy", p2.hasPeanutAllergy());
        System.out.println("Finished testDietaryRestriction");

    }

    @Test
    public void testShowDietaryProducts() {
        System.out.println("\nGet dietaryRestricted products");
        list = new ArrayList<>();
        list.add(new Product( "6849","Rye Bread", true));
        list.add(new Product( "6917","Whole Wheat Bread", true));
        list.add(new Product( "3818","Lucky Charms",true));
        list.add(new Product( "1958","12 cookies", true));

        List<Product> testList = accessProducts.getDietaryProducts();
        assertTrue("Should return a list of the same size", list.size() == testList.size());



        System.out.println("Finished testShowDietaryProducts");

   }

}