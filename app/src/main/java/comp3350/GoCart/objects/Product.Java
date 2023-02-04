package comp3350.gocart.objects;

import java.util.objects;
import java.math.BigDecimal;

public class Product{

    
    private final String productName;
    private final BigDecimal productPrice;
    //private final arraylist<string> allergen?

    public Product(final int newIndex, final String newProductName, final BigDecimal newProductPrice){
        productindex = newIndex;
        productName = newProductName;
        productPrice = newProductPrice;
    }


    public String getProductName(){
        return productName;
    }

    public Bigdecimal getProductPrice(){
        return productPrice;
    }




}   