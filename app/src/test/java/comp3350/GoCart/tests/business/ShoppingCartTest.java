package comp3350.GoCart.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.GetByName;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartTest extends TestCase{

    @Test
    public void testValidData(){
        AccessProducts products = new AccessProducts();
        List<Product> prods = products.getAllProducts();


        for (int i = 0; i<  prods.size(); i++){
            System.out.println(prods.get(i));
        }
        System.out.println("HERE");
        ShoppingCart cart = new ShoppingCart();
        System.out.println("HERE4");
        cart.addProduct(prods.get(0) , 5 );
        cart.addProduct(prods.get(1) , 6 );
        System.out.println("HERE2 ");
        HashMap<Product,Integer> itemsInCart = cart.getCartList();
        System.out.println("HERE3");
        System.out.println("AA");
        System.out.println(itemsInCart.get(prods.get(0)).toString());
        System.out.println(itemsInCart.get(prods.get(1)).toString());
    }
}
