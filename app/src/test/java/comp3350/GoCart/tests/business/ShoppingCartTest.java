package comp3350.GoCart.tests.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.mock;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.*;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;


import java.math.BigDecimal;
import java.util.List;


public class ShoppingCartTest{
    private ShoppingCart cart;
    private Product product1,product2;



    @Before
    public void init(){
        product1 = new Product("4521","Banana",false,"produce");
        product2 = new Product("6849","Rye Bread",true,"bakery");
        System.out.println("Init executed");
    }


    @After
    public void clearCart(){
        cart.clearCart();
        System.out.println("Test Cleanup\n");
    }


    @Test
    public void testValidData(){
        System.out.println("Start Test: cart valid data");

        cart = ShoppingCart.getInstance();
        cart.addProduct(product1 , 5 );
        cart.addProduct(product2 , 6 );

        assertTrue("List contains first item ", cart.isInCart(product1 ));
        assertEquals("first item has quantity 5",Integer.valueOf(5), cart.getQuantity(product1));
        assertTrue("List contains second item ", cart.isInCart(product2));
        assertEquals("first item has quantity 6",Integer.valueOf(6), cart.getQuantity(product2));

        System.out.println("End Test: cart valid data");
    }

    @Test
    public void testValidIncrement(){
        System.out.println("Start Test: cart valid increment");

        cart = ShoppingCart.getInstance();
        cart.addProduct(product1 , 1 );

        assertTrue("List contains fifth item ", cart.isInCart(product1));
        assertEquals("banana has quantity of 1", Integer.valueOf(1),cart.getQuantity(product1));
        cart.incrementProductQuantity(product1);
        assertEquals("banana has quantity of 2", Integer.valueOf(2),cart.getQuantity(product1));
        cart.incrementProductQuantity(product1);
        assertEquals("banana has quantity of 3", Integer.valueOf(3),cart.getQuantity(product1));
        System.out.println("End Test: cart valid increment");
    }

    @Test
    public void testinvalidIncrement(){
        System.out.println("Start Test: cart invalid increment ");

        cart = ShoppingCart.getInstance();
        assertEquals("fbanana has quantity 0",Integer.valueOf(0),cart.getQuantity(product1));
        cart.incrementProductQuantity(product1);
        assertEquals("banana still has quantity 0",Integer.valueOf(0),cart.getQuantity(product1));
        System.out.println("End Test: cart invalid increment ");
    }

    @Test
    public void testValidDecrement(){
        System.out.println("Start Test: cart valid decrement ");

        cart = ShoppingCart.getInstance();
        cart.addProduct(product1 , 7);
        assertTrue("List contains product 4 item ",  cart.isInCart(product1) );
        assertEquals("banana has quantity of 7", Integer.valueOf(7),cart.getQuantity(product1));
        cart.decrementProductQuantity(product1);
        assertEquals("banana has quantity of 6", Integer.valueOf(6),cart.getQuantity(product1));
        cart.decrementProductQuantity(product1);
        assertEquals("banana has quantity of 5", Integer.valueOf(5),cart.getQuantity(product1));
        System.out.println("End Test: cart valid decrement ");
    }
    @Test
    public void testinvaliddecrement(){
        System.out.println("Start Test: cart invalid decrement");

        cart = ShoppingCart.getInstance();

        assertEquals("banana not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(product1));
        cart.decrementProductQuantity(product1);
        assertEquals("banana not in list, expected 0 value", Integer.valueOf(0) ,cart.getQuantity(product1));
        System.out.println("End Test: cart invalid decrement");
    }

    @Test
    public void testValidChangeQuantity(){
        System.out.println("Start Test: cart valid change quantity");

        cart = ShoppingCart.getInstance();

        cart.addProduct(product1 , 2 );
        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(product1));
        cart.changeProductQuantity(product1,12);
        assertEquals("product 0 has a quantity of 12", Integer.valueOf(12),cart.getQuantity(product1));

        System.out.println("End Test: cart valid change quantity");
    }

    @Test
    public void testInvalidChangeQuantity(){
        System.out.println("Start Test: cart invalid change quantity");
        cart = ShoppingCart.getInstance();

        cart.addProduct(product1 , 2 );
        assertEquals("product 1 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(product1));

        cart.changeProductQuantity(product2,5);
        assertEquals("product 2 not in cart and has 0 quantity",Integer.valueOf(0),cart.getQuantity(product2));

        cart.changeProductQuantity(product2,12);
        assertEquals("product 2 not in cart and has 0 quantity",Integer.valueOf(0),cart.getQuantity(product2));

        cart.addProduct(product2,2);
        assertEquals("product 2 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(product2));

        cart.removeProduct(product2);
        assertEquals("product 2  has 0 quantity",Integer.valueOf(0),cart.getQuantity(product2));
        System.out.println("End Test: cart invalid change quantity");
    }


    @Test
    public void testremoveProduct(){
        System.out.println("Start Test: cart remove product");

        cart = ShoppingCart.getInstance();
        cart.addProduct(product1 , 2 );

        assertEquals("product 0 has a quantity of 2", Integer.valueOf(2),cart.getQuantity(product1));
        cart.removeProduct(product1);
        assertEquals("product 0 not in list, expected 0", Integer.valueOf(0),cart.getQuantity(product1));
        System.out.println("End Test: cart remove product");
    }

    @Test
    public void testSetandGetStore(){
        System.out.println("Start Test: cart set and get store");
        Store store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");

        cart = ShoppingCart.getInstance();
        cart.setStore(store1);
        assertEquals("should be same object pass to it",store1,cart.getStore());
        System.out.println("End Test: cart set and get store");
    }
    @Test
    public void testCalculateTotal(){
        System.out.println("Start Test: cart calculate total");
        AccessStoreProduct asp = mock(AccessStoreProduct.class);
        Store store = new Store("1","Walmart", "1576 Regent Ave Winnipeg");
        when(asp.calculateTotal(anyList() ,anyList() ,any())).thenReturn(BigDecimal.valueOf(12.34));

        Store store1 = new Store("1","Walmart", "1576 Regent Ave Winnipeg");

        cart = ShoppingCart.getInstance();
        cart.setStore(store1);
        cart.addProduct(product1,5);
        cart.addProduct(product2,5);

        assertEquals("final price is ",BigDecimal.valueOf(12.34),cart.calculateTotal(asp));
        verify(asp).calculateTotal(anyList() ,anyList() ,any());

        System.out.println("End Test: cart calculate total");
    }

}

