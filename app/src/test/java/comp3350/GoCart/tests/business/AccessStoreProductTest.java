package comp3350.GoCart.tests.business;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.ProductPersistence;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.persistence.StoreProductPersistence;


public class AccessStoreProductTest {
    private AccessStoreProduct accessStoreProduct;
    private Product product1,product2,product3;
    private Store store1, store2;
    private StoreProduct storeProduct1,storeProduct2,storeProduct3,storeProduct4;
    private List<StoreProduct> allStoreProducts, store1ProductList;
    private StoreProductPersistence mockStoreProductPersistence;
    private ProductPersistence mockProductPersistence;

    @Before
    public void initalize(){

        store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");
        store2 = new Store("2", "Costco", "1499 Regent Ave W Winnipeg");
        product1 = new Product("4521","Banana",false,"produce");
        product2 = new Product("6849","Rye Bread",true, "bakery");
        product3 = new Product("6917", "Whole Wheat Bread",true,"bakery");

        storeProduct1 = new StoreProduct(store1,product1,new BigDecimal(1.00));
        storeProduct2 = new StoreProduct(store1,product2,new BigDecimal(2.00));
        storeProduct3 = new StoreProduct(store1,product3,new BigDecimal(3.00));
        storeProduct4 = new StoreProduct(store2, product3, new BigDecimal(2.00));

        allStoreProducts = new ArrayList<>();
        allStoreProducts.add(storeProduct1);
        allStoreProducts.add(storeProduct2);
        allStoreProducts.add(storeProduct3);
        allStoreProducts.add(storeProduct4);

        store1ProductList = new ArrayList<>();
        store1ProductList.add(storeProduct1);
        store1ProductList.add(storeProduct2);
        store1ProductList.add(storeProduct3);

        // Mock for store product persistence
        mockStoreProductPersistence = mock(StoreProductPersistence.class);
        // Mock for store persistence. Needed for category selection
        mockProductPersistence = mock(ProductPersistence.class);

        // Setup mock returns for store product persistence
        when(mockStoreProductPersistence.getStoreProducts("1")).thenReturn(store1ProductList);
        when(mockStoreProductPersistence.getAllStoreProducts()).thenReturn(allStoreProducts);

        // Setup mock returns for product persistence

        // Get products from Bakery category
        List<Product> bakeryProducts = new ArrayList<>();
        bakeryProducts.add(product2);
        bakeryProducts.add(product3);
        when(mockProductPersistence.productCategory("bakery")).thenReturn(bakeryProducts);

        // Get products from Produce category
        List<Product> produceProducts = new ArrayList<>();
        produceProducts.add(product1);
        when(mockProductPersistence.productCategory("produce")).thenReturn(produceProducts);

        // Get dietary restricted products
        // All produce products are peanut free
        when(mockProductPersistence.getDietaryRestrictedProducts()).thenReturn(produceProducts);

        // Create accessStoreProduct using mocks
        accessStoreProduct = new AccessStoreProduct(mockStoreProductPersistence, mockProductPersistence);
    }

    @Test
    public void testInvalidStoreValidProduct() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("9", "Banana");
        assertTrue("This store should not exist", result.isEmpty());
    }

    @Test
    public void testValidStoreInValidProduct() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "Apple");
        assertTrue("This product should not exist", result.isEmpty());
    }

    @Test
    public void testUpperAndLowerCase() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "rYe BrEaD");
        assertTrue("The product returned should still be rye bread despite letter case", result.get(0).getProductName().equals("Rye Bread"));
    }

    @Test
    public void testPartialSearch() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "bread");
        assertTrue("The product returned should be Rye Bread and Whole Wheat Bread", (result.get(0).getProductName().equals("Rye Bread") &&
                result.get(0).getProductName().equals("Whole Wheat Bread") || (result.get(0).getProductName().equals("Whole Wheat Bread")
                || result.get(0).getProductName().equals("Rye Bread"))));
    }

    @Test
    public void testValidSearchCategory() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "Rye Bread", "bakery");
        assertTrue("The product returned should be Rye Bread", (result.get(0).getProductName().equals("Rye Bread")));
    }

    @Test
    public void testInvalidSearchCategory() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByName("1", "Banana", "dairy");
        assertTrue("Result should be empty since banana is not a dairy product", (result.isEmpty()));
    }

    @Test
    public void testSearchCategoryWithRestriction() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByNameWithAllergen("1", "Banana", "produce");
        assertTrue("Results should contain banana since it is a produce and is also peanut free", (result.get(0).getProductName().equals("Banana")));
    }

    @Test
    public void testSearchCategoryWithRestrictionFail() {
        List<StoreProduct> result = accessStoreProduct.getStoreProductsByNameWithAllergen("1", "Rye Bread", "bakery");
        assertTrue("Results should be empty. Rye Bread from the bakery category has peanuts in it", (result.isEmpty()));
    }

    @Test
    public void testFindCheapestStoreThatSellsProduct() {
        StoreProduct result = accessStoreProduct.findCheapestStore(product3);
        assertTrue("Costco sells Whole Wheat Bread at a cheaper price compared to Walmart", result.getStoreId().equals(store2.getStoreID()));
    }

    // Find average price of Whole Wheat bread from all stores
    @Test
    public void testFindAveragePrice() {
        BigDecimal result = accessStoreProduct.findAveragePrice(product3);

        // Find expected average
        BigDecimal expectedResult = storeProduct3.getPrice().add(storeProduct4.getPrice());
        expectedResult = expectedResult.divide(BigDecimal.valueOf(2), 2, RoundingMode.DOWN);    // divide by 2, round down to 2 decimal places

        assertTrue("Average price should be the sum of StoreProduct prices divided by the number of StoreProducts with the same product", result.equals(expectedResult));
    }
}
