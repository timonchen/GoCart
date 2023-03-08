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
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains first item ",  itemsInCart.containsKey(prods.get(0)));
        assertEquals("first item has quantity 5",Integer.valueOf(5), itemsInCart.get(prods.get(0)));
        assertTrue("List contains second item ", itemsInCart.containsKey(prods.get(1)));
        assertEquals("first item has quantity 5",Integer.valueOf(6), itemsInCart.get(prods.get(1)));
        cart.clearCart();

    }
    @Test
    public void testValidIncrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(5) , 1 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains fifth item ",  itemsInCart.containsKey(prods.get(5)) );
        assertEquals("product 5 has quantity of 1", Integer.valueOf(1),itemsInCart.get(prods.get(5)));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 2", Integer.valueOf(2),itemsInCart.get(prods.get(5)));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 3", Integer.valueOf(3),itemsInCart.get(prods.get(5)));
        cart.clearCart();
    }

    @Test
    public void testinvalidIncrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("first item has quantity 1",null,itemsInCart.get(prods.get(1)) );
        cart.incrementProductQuantity(prods.get(1));
        assertEquals("first item has quantity 1",null,itemsInCart.get(prods.get(1)));
        cart.clearCart();
    }

    @Test
    public void testValidDecrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(4) , 7);
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains product 4 item ",  itemsInCart.containsKey(prods.get(4)));
        assertEquals("product 4 has quantity of 7", Integer.valueOf(7),itemsInCart.get(prods.get(4)));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 6", Integer.valueOf(6),itemsInCart.get(prods.get(4)));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 5", Integer.valueOf(5),itemsInCart.get(prods.get(4)));
        cart.clearCart();
    }
    @Test
    public void testinvaliddecrement(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("Item not in list, expected null value", null ,itemsInCart.get(prods.get(2)) );
        cart.decrementProductQuantity(prods.get(2));
        assertEquals("Item not in list, expected null value", null ,itemsInCart.get(prods.get(2)));
        cart.clearCart();
    }

    @Test
    public void testValidChangeQuantity(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(0) , 2 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),itemsInCart.get(prods.get(0)) );
        cart.changeProductQuantity(prods.get(0),12);
        assertEquals("product 0 has a quantity of 12", Integer.valueOf(12),itemsInCart.get(prods.get(0)) );
        cart.clearCart();
    }

    @Test
    public void testInvalidChangeQuantity(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();

        cart.addProduct(prods.get(0) , 2 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),itemsInCart.get(prods.get(0)) );

        cart.changeProductQuantity(prods.get(1),12);
        assertEquals("product 1 has null quantity",null,itemsInCart.get(prods.get(1)) );

        cart.changeProductQuantity(prods.get(2),12);
        assertEquals("product 2  has null quantity",null,itemsInCart.get(prods.get(2)) );

        cart.addProduct(prods.get(2),2);
        assertEquals("product 2 has a quantity of 2", Integer.valueOf(2),itemsInCart.get(prods.get(2)) );

        cart.removeProduct(prods.get(2));
        assertEquals("product 2  has null quantity",null,itemsInCart.get(prods.get(2)) );
        cart.clearCart();
    }


    @Test
    public void testremoveProduct(){
        prods = products.getProducts();
        cart = ShoppingCart.getInstance();
        cart.addProduct(prods.get(0) , 2 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),itemsInCart.get(prods.get(0)) );
        cart.removeProduct(prods.get(0));
        assertEquals("product 0 not in list, expected null", null,itemsInCart.get(prods.get(0)) );
        cart.clearCart();
    }
}
