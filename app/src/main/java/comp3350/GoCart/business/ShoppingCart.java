package comp3350.GoCart.business;


import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

public class ShoppingCart {
    private static volatile ShoppingCart instance = null;
    //private HashMap<Product,Integer> cartList = new HashMap<Product, Integer>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();
    private Store store;

    private ShoppingCart() {
    }

    public void setStore(Store newStore){
        store = newStore;
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
        //cartList.put(newProd, newQuantity);
        products.add(newProd);
        quantity.add(newQuantity);
        System.out.println("NEW ITEM ADDED " + newProd+  newQuantity);

    }

    public void removeProduct(Product newProd){
        //cartList.remove(newProd);
        int position = products.indexOf(newProd);
        products.remove(position);
        quantity.remove(position);
    }

    public void changeProductQuantity(Product newProd, Integer newQuant){
        /*
        if (cartList.containsKey(newProd)){
            cartList.put(newProd,newQuant);
        } /*else {
            // throw exception product not in list
        }*/

        if ( products.contains(newProd)){
            quantity.set(products.indexOf(newProd),newQuant);
        }
    }

    public void incrementProductQuantity(Product newProd){
        /*
        if (cartList.containsKey(newProd)) {
            changeProductQuantity(newProd,cartList.get(newProd) + 1 );
        }
         */
        if(products.contains(newProd)){
            changeProductQuantity(newProd,quantity.get(products.indexOf(newProd)) +1 );
        }
    }

    public void decrementProductQuantity(Product newProd){
        /*
        if (cartList.containsKey(newProd)) {
            changeProductQuantity(newProd,cartList.get(newProd) - 1 );
        }
        */
        if(products.contains(newProd)){
            changeProductQuantity(newProd,quantity.get(products.indexOf(newProd)) - 1 );
        }
    }

    /*
    public HashMap<Product, Integer> getCartList(){
        return cartList;
    }
        public void clearCart(){
        cartList = new HashMap<Product, Integer>();
    }
    */
    public List<Product> getCartProducts(){
        return Collections.unmodifiableList(products);
    }
    public List<Integer> getCartQuantity(){
        return Collections.unmodifiableList(quantity);
    }

    public void clearCart(){
        products.clear();
        quantity.clear();
    }

    public Boolean isInCart(Product newProd){
        return products.contains(newProd);
    }

    public Integer getQuantity(Product newProd) {
        if (products.contains(newProd))
            return quantity.get(products.indexOf(newProd));
        return 0;

    }





}
