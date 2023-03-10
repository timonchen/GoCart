package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.stubs.ProductPersistenceStub;
import comp3350.GoCart.persistence.stubs.StorePersistenceStub;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartTest extends TestCase{
    private List<Product> prods;
    private ShoppingCart cart;
    AccessProducts products = new AccessProducts(new ProductPersistenceStub());
    AccessStores accessStores = new AccessStores(new StorePersistenceStub());



    @After
    public void clearCart(){
        cart.clearCart();
    }


    @Test
    public void testValidData(){
        List<Product> products1 = products.searchProductsByName("Banana");
        List<Product> products2 = products.searchProductsByName("Rye");

        cart = ShoppingCart.getInstance();
        cart.addProduct(products1.get(0) , 5 );
        cart.addProduct(products2.get(0) , 6 );
        assertTrue("List contains first item ", cart.isInCart(products1.get(0)) );
        assertEquals("first item has quantity 5",Integer.valueOf(5), cart.getQuantity(products1.get(0)));
        assertTrue("List contains second item ", cart.isInCart(products1.get(0)) );
        assertEquals("first item has quantity 6",Integer.valueOf(6), cart.getQuantity(products2.get(0)));

        cart.clearCart();

    }

    @Test
    public void testValidIncrement(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();
        cart.addProduct(products1.get(0) , 1 );

        assertTrue("List contains fifth item ", cart.isInCart(banana));
        assertEquals("banana has quantity of 1", Integer.valueOf(1),cart.getQuantity(banana));
        cart.incrementProductQuantity(banana);
        assertEquals("banana has quantity of 2", Integer.valueOf(2),cart.getQuantity(banana));
        cart.incrementProductQuantity(banana);
        assertEquals("banana has quantity of 3", Integer.valueOf(3),cart.getQuantity(banana));
        cart.clearCart();
    }

    @Test
    public void testinvalidIncrement(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();
        assertEquals("fbanana has quantity 0",Integer.valueOf(0),cart.getQuantity(banana));
        cart.incrementProductQuantity(banana);
        assertEquals("banana still has quantity 0",Integer.valueOf(0),cart.getQuantity(banana));
        cart.clearCart();
    }

    @Test
    public void testValidDecrement(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();
        cart.addProduct(banana , 7);
        assertTrue("List contains product 4 item ",  cart.isInCart(banana) );
        assertEquals("banana has quantity of 7", Integer.valueOf(7),cart.getQuantity(banana));
        cart.decrementProductQuantity(banana);
        assertEquals("banana has quantity of 6", Integer.valueOf(6),cart.getQuantity(banana));
        cart.decrementProductQuantity(banana);
        assertEquals("banana has quantity of 5", Integer.valueOf(5),cart.getQuantity(banana));
        cart.clearCart();
    }
    @Test
    public void testinvaliddecrement(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();

        assertEquals("banana not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(banana));
        cart.decrementProductQuantity(banana);
        assertEquals("banana not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(banana));
        cart.clearCart();
    }

    @Test
    public void testValidChangeQuantity(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();

        cart.addProduct(banana , 2 );
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(banana));
        cart.changeProductQuantity(banana,12);
        assertEquals("product 0 has a quantity of 12", Integer.valueOf(12),cart.getQuantity(banana));
        cart.clearCart();
    }

    @Test
    public void testInvalidChangeQuantity(){

        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);
        List<Product> products2 = products.searchProductsByName("Rye");
        Product rye = products2.get(0);
        List<Product> products3 = products.searchProductsByName("Cookie");
        Product cookie = products3.get(0);

        cart = ShoppingCart.getInstance();

        cart.addProduct(banana , 2 );
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(banana));

        cart.changeProductQuantity(rye,12);
        assertEquals("product 1 has 0 quantity",Integer.valueOf(0),cart.getQuantity(rye));

        cart.changeProductQuantity(cookie,12);
        assertEquals("product 2  has 0 quantity",Integer.valueOf(0),cart.getQuantity(cookie));

        cart.addProduct(cookie,2);
        assertEquals("product 2 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(cookie));

        cart.removeProduct(cookie);
        assertEquals("product 2  has 0 quantity",Integer.valueOf(0),cart.getQuantity(cookie));
        cart.clearCart();
    }


    @Test
    public void testremoveProduct(){
        List<Product> products1 = products.searchProductsByName("Banana");
        Product banana = products1.get(0);

        cart = ShoppingCart.getInstance();
        cart.addProduct(banana , 2 );

        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(banana));
        cart.removeProduct(banana);
        assertEquals("product 0 not in list, expected 0", Integer.valueOf(0),cart.getQuantity(banana));
        cart.clearCart();
    }

    @Test
    public void testSetandGetStore(){
        cart = ShoppingCart.getInstance();
        Store zero = accessStores.getStores().get(0);
        cart.setStore(zero);
        assertEquals("should be same object pass to it",zero,cart.getStore());
        cart.clearCart();
    }









}
