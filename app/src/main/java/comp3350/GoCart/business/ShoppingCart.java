package comp3350.GoCart.business;


import android.content.Intent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

public class ShoppingCart {
    private static final ShoppingCart instance = new ShoppingCart();

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();
    private Store store;

    private ShoppingCart() {

    }

    public void setStore(Store newStore){
        store = newStore;
    }
    public Store getStore(){
        return store;
    }


    public BigDecimal calculateTotal(AccessStoreProduct ap){
        return ap.calculateTotal(products,quantity,store);
    }

    public static ShoppingCart getInstance(){
        return instance;
    }

    public void addProduct(Product newProd, Integer newQuantity){
        //cartList.put(newProd, newQuantity);
        if ( products.contains(newProd)) {
            changeProductQuantity(newProd,quantity.get(products.indexOf(newProd)) + newQuantity);
        } else {
            products.add(newProd);
            quantity.add(newQuantity);
        }
    }

    public void removeProduct(Product newProd){
        //cartList.remove(newProd);
        int position = products.indexOf(newProd);
        products.remove(position);
        products.trimToSize();
        quantity.remove(position);
        quantity.trimToSize();
    }

    public void changeProductQuantity(Product newProd, Integer newQuant){

        if ( products.contains(newProd)){
            if ( newQuant > -1) {
                quantity.set(products.indexOf(newProd), newQuant);
            } else
                removeProduct(newProd);
        }
    }

    public void incrementProductQuantity(Product newProd){

        if(products.contains(newProd)){
            changeProductQuantity(newProd,quantity.get(products.indexOf(newProd)) +1 );
        }
    }

    public void decrementProductQuantity(Product newProd){
        if(products.contains(newProd)){
            changeProductQuantity(newProd,quantity.get(products.indexOf(newProd)) - 1 );
        }
    }


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
        if (products.contains(newProd)) {
            return quantity.get(products.indexOf(newProd));
        }
        return 0;

    }



}
