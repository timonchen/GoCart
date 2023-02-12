package comp3350.GoCart.objects;

import java.math.BigDecimal;

public class Product {
    private final String productName;
    private final BigDecimal productPrice;

    private  final boolean peanutAllergy;
    public Product(final String newProductName, final BigDecimal newProductPrice, boolean peanutAllergy){
        productName = newProductName;
        productPrice = newProductPrice;
        this.peanutAllergy = peanutAllergy;
    }

    public Product(final String newProductName){
        productName = newProductName;
        productPrice = BigDecimal.ZERO;
        this.peanutAllergy = false;
    }


    public String getProductName(){
        return productName;
    }

    public BigDecimal getProductPrice(){
        return productPrice;
    }

    public boolean hasPeanutAllergy(){return peanutAllergy ;   }
    public boolean equals(Object other) {
        if(other instanceof Product) {
            Product toCheck = (Product) other;
            return productName.equals(toCheck.productName);
        }

        return false;
    }

    public String toString(){
        return "Product: " + productName + "price: " + productPrice+" Peanut Allergy:" +hasPeanutAllergy();
    }

}   