package comp3350.GoCart.tests.business;
import junit.framework.TestCase;
import org.junit.Test;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.objects.Product;
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
        accessProducts = new AccessProducts();
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
        Product p1 = new Product("product1", "Banana", false);
        Product p2 = (new Product("product2", "Rye Bread", true));
        assertFalse("The product should have peanut allergy", p1.hasPeanutAllergy());
        assertTrue("The product should not have peanut allergy", p2.hasPeanutAllergy());
        System.out.println("Finished testDietaryRestriction");

    }

    @Test
    public void testShowDietaryProducts() {
        System.out.println("\nGet dietaryRestricted products");
        list = new ArrayList<>();
        list.add(new Product("product1", "Banana", false));
        list.add(new Product("product2", "Beef Jerkey", false));
        list.add(new Product("product3", "toilet paper", false));
        list.add(new Product("product4", "Ice-cream", false));

        List<Product> testList = accessProducts.getDietaryProducts();
        assertTrue(testList.containsAll(list) && (list).containsAll(testList));

        System.out.println("Finished testShowDietaryProducts");

   }


}