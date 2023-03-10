package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.stubs.StoreProductPersistenceStub;


public class SearchStoreProductTest extends TestCase {
    private AccessStoreProduct accessStoreProduct;

    public SearchStoreProductTest() {
        accessStoreProduct = new AccessStoreProduct(new StoreProductPersistenceStub());
    }

    @Test
    public void testInvalidStoreValidProduct() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("super252", "Banana");
        assertTrue("This store should not exist", result.isEmpty());
    }

    @Test
    public void testValidStoreInValidProduct() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("cost149", "Apple");
        assertTrue("This product should not exist", result.isEmpty());
    }

    @Test
    public void testUpperAndLowerCase() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("cost149", "rYe BrEaD");
        assertTrue("The product returned should still be rye bread despite letter case", result.get(0).getProductName().equals("Rye Bread"));
    }

    @Test
    public void testPartialSearch() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("safe202", "bread");
        assertTrue("The product returned should be Rye Bread and Whole Wheat Bread", (result.get(0).getProductName().equals("Rye Bread") &&
                result.get(0).getProductName().equals("Whole Wheat Bread") || (result.get(0).getProductName().equals("Whole Wheat Bread") || result.get(0).getProductName().equals("Rye Bread"))));
    }
}
