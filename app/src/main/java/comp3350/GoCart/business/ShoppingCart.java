package comp3350.GoCart.business;


import android.annotation.SuppressLint;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import comp3350.GoCart.objects.Product;

public class ShoppingCart {
    private static volatile ShoppingCart instance = null;
    private HashMap<Product,Integer> cartList = new HashMap<Product, Integer>();;

    private void ShoppingCart() {

    }

    public static ShoppingCart getInstance(){
        if (instance == null) {
            synchronized (ShoppingCart.class) {
                if (instance == null) {
                    instance = new ShoppingCart();
                }
            }
        }
            return instance;
    }

    public void addProduct(Product newProd, Integer newQuantity){

        cartList.put(newProd, newQuantity);
    }

    public void removeProduct(Product newProd){
        cartList.remove(newProd);
    }

    public void changeProductQuantity(Product newProd, Integer newQuant){
        //cartList.put(newProd, cartList.getOrDefault(newProd,0) + newQuant);
        if (cartList.containsKey(newProd)){
            cartList.put(newProd,newQuant);
        } else {
            // throw exception product not in list
        }
    }

    public void incrementProductQuantity(Product newProd){
        if (cartList.containsKey(newProd)) {
            changeProductQuantity(newProd,cartList.get(newProd) + 1 );
        }
    }

    public void decrementProductQuantity(Product newProd){
        if (cartList.containsKey(newProd)) {
            changeProductQuantity(newProd,cartList.get(newProd) - 1 );
        }
    }


    public HashMap<Product,Integer> getCartList(){
        return cartList;
    }


}
