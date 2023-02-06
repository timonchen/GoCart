package comp3350.GoCart.objects;

import java.math.BigDecimal;

public class Product {
    private final String productName;
    private final BigDecimal productPrice;

    public Product(final String newProductName, final BigDecimal newProductPrice){
        productName = newProductName;
        productPrice = newProductPrice;
    }


    public String getProductName(){
        return productName;
    }

    public BigDecimal getProductPrice(){
        return productPrice;
    }


    public boolean equals(Object other) {
        if(other instanceof Product) {
            Product toCheck = (Product) other;
            return productName.equals(toCheck.productName);
        }

        return false;
    }

    public String toString(){
        return "Product: " + productName + "price: " + productPrice;
    }

}   