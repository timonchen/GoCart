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



    @Test
    public void testValidData(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        cart.addProduct(prods.get(0) , 5 );
        cart.addProduct(prods.get(1) , 6 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains first item ",  itemsInCart.containsKey(prods.get(0)));
        assertEquals("first item has quantity 5",itemsInCart.get(prods.get(0)) , Integer.valueOf(5));
        assertTrue("List contains second item ", itemsInCart.containsKey(prods.get(1)));
        assertEquals("first item has quantity 5",itemsInCart.get(prods.get(1)) , Integer.valueOf(6));
    }
    @Test
    public void testValidIncrement(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        cart.addProduct(prods.get(5) , 1 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains fifth item ",  itemsInCart.containsKey(prods.get(5)) );
        assertEquals("product 5 has quantity of 1",itemsInCart.get(prods.get(5)) , Integer.valueOf(1));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 2",itemsInCart.get(prods.get(5)) , Integer.valueOf(2));
        cart.incrementProductQuantity(prods.get(5));
        assertEquals("product 5 has quantity of 3",itemsInCart.get(prods.get(5)) , Integer.valueOf(3));
    }

    @Test
    public void testinvalidIncrement(){
        prods = products.getProducts();
        cart = new ShoppingCart();

        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("first item has quantity 1",itemsInCart.get(prods.get(1)) , null);
        cart.incrementProductQuantity(prods.get(1));
        assertEquals("first item has quantity 1",itemsInCart.get(prods.get(1)) ,null);
    }

    @Test
    public void testValidDecrement(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        cart.addProduct(prods.get(4) , 7);
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertTrue("List contains product 4 item ",  itemsInCart.containsKey(prods.get(4)));
        assertEquals("product 4 has quantity of 7",itemsInCart.get(prods.get(4)) , Integer.valueOf(7));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 6",itemsInCart.get(prods.get(4)) , Integer.valueOf(6));
        cart.decrementProductQuantity(prods.get(4));
        assertEquals("product 4 has quantity of 5",itemsInCart.get(prods.get(4)) , Integer.valueOf(5));
    }
    @Test
    public void testinvaliddecrement(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("Item not in list, expected null value",itemsInCart.get(prods.get(2)) , null);
        cart.decrementProductQuantity(prods.get(2));
        assertEquals("Item not in list, expected null value",itemsInCart.get(prods.get(2)) ,null);
    }

    @Test
    public void testValidChangeQuantity(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        cart.addProduct(prods.get(0) , 2 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("product 0 has a quantity of 2",itemsInCart.get(prods.get(0)) , Integer.valueOf(2));
        cart.changeProductQuantity(prods.get(0),12);
        assertEquals("product 0 has a quantity of 12",itemsInCart.get(prods.get(0)) ,Integer.valueOf(12));
    }

    @Test
    public void testremoveProduct(){
        prods = products.getProducts();
        cart = new ShoppingCart();
        cart.addProduct(prods.get(0) , 2 );
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        assertNotNull(itemsInCart);
        assertEquals("product 0 has a quantity of 2",itemsInCart.get(prods.get(0)) , Integer.valueOf(2));
        cart.removeProduct(prods.get(0));
        assertEquals("product 0 not in list, expected null",itemsInCart.get(prods.get(0)) , null);
    }


}
