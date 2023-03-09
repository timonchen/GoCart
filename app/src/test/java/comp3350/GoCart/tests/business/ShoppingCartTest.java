package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.GetByName;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartTest extends TestCase{
    private List<Product> prods;
    private ShoppingCart cart;
    AccessProducts products = new AccessProducts();



    @After
    public void clearCart(){
        cart.clearCart();
    }


    @Test
    public void testValidData(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(0) , 5 );
        cart.addProduct(prods.get(1) , 6 );
        assertTrue("List contains first item ", cart.isInCart(prods.get(0)) );
        assertEquals("first item has quantity 5",Integer.valueOf(5), cart.getQuantity(prods.get(0)));
        assertTrue("List contains second item ", cart.isInCart(prods.get(1)) );
        assertEquals("first item has quantity 5",Integer.valueOf(6), cart.getQuantity(prods.get(1)));
        System.out.println("SUC");
        cart.clearCart();

    }

    @Test
    public void testValidIncrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(5) , 1 );

        assertTrue("List contains fifth item ", cart.isInCart(prods.get(5)));
        assertEquals("product 5 has quantity of 1", Integer.valueOf(1),cart.getQuantity(prods.get(5)));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 2", Integer.valueOf(2),cart.getQuantity(prods.get(5)));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 3", Integer.valueOf(3),cart.getQuantity(prods.get(5)));
        cart.clearCart();
    }

    @Test
    public void testinvalidIncrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        assertEquals("first item has quantity 0",Integer.valueOf(0),cart.getQuantity(prods.get(1)));
        cart.incrementProductQuantity(prods.get(1));
        assertEquals("first item has quantity 0",Integer.valueOf(0),cart.getQuantity(prods.get(1)));
        cart.clearCart();
    }

    @Test
    public void testValidDecrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(4) , 7);
        assertTrue("List contains product 4 item ",  cart.isInCart(prods.get(4)) );
        assertEquals("product 4 has quantity of 7", Integer.valueOf(7),cart.getQuantity(prods.get(4)));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 6", Integer.valueOf(6),cart.getQuantity(prods.get(4)));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 5", Integer.valueOf(5),cart.getQuantity(prods.get(4)));
        cart.clearCart();
    }
    @Test
    public void testinvaliddecrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        assertEquals("Item not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(prods.get(4)));
        cart.decrementProductQuantity(prods.get(2));
        assertEquals("Item not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(prods.get(4)));
        cart.clearCart();
    }

    @Test
    public void testValidChangeQuantity(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        cart.addProduct(prods.get(0) , 2 );
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(prods.get(0)));
        cart.changeProductQuantity(prods.get(0),12);
        assertEquals("product 0 has a quantity of 12", Integer.valueOf(12),cart.getQuantity(prods.get(0)));
        cart.clearCart();
    }

    @Test
    public void testInvalidChangeQuantity(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        cart.addProduct(prods.get(0) , 2 );

        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(prods.get(0)));

        cart.changeProductQuantity(prods.get(1),12);
        assertEquals("product 1 has 0 quantity",Integer.valueOf(0),cart.getQuantity(prods.get(1)));
        cart.changeProductQuantity(prods.get(2),12);
        assertEquals("product 2  has 0 quantity",Integer.valueOf(0),cart.getQuantity(prods.get(2)));

        cart.addProduct(prods.get(2),2);
        assertEquals("product 2 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(prods.get(2)));

        cart.removeProduct(prods.get(2));
        assertEquals("product 2  has 0 quantity",Integer.valueOf(0),cart.getQuantity(prods.get(2)));
        cart.clearCart();
    }


    @Test
    public void testremoveProduct(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(0) , 2 );

        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(prods.get(0)));
        cart.removeProduct(prods.get(0));
        assertEquals("product 0 not in list, expected 0", Integer.valueOf(0),cart.getQuantity(prods.get(0)));
        cart.clearCart();
    }


}
