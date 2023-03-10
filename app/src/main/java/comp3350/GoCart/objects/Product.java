package comp3350.GoCart.objects;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Product {

    private String productID;
    private final String productName;



    private  final boolean peanutAllergy;
    public Product(final String newProductID, final String newProductName,boolean peanutAllergy){
        productID = newProductID;
        productName = newProductName;
        this.peanutAllergy = peanutAllergy;
    }



    public String getProductName(){
        return productName;
    }

    public String getProductID() {return productID; }

    public boolean hasPeanutAllergy(){return peanutAllergy ;   }
    public boolean equals(Object otherObject){
        boolean result = false;
        if ( otherObject instanceof Product){
            final Product otherProduct = (Product) otherObject;
            result = Objects.equals(this.productID,otherProduct.productID);
        }
        return result;
    }

    @NonNull
    public String toString(){
        return "Product: " + productName + " Peanut Allergy:" +hasPeanutAllergy();
    }
}