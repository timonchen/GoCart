package comp3350.GoCart.objects;


import java.util.Objects;

public class Product {

    private String productID;
    private final String productName;
    private final String category;


    private  final boolean peanutAllergy;
    public Product(final String newProductID, final String newProductName,boolean peanutAllergy, final String category){
        productID = newProductID;
        productName = newProductName;
        this.peanutAllergy = peanutAllergy;
        this.category = category;
    }

    public Product(final String newProductID, final String newProductName){
        productName = newProductName;
        productID = newProductID;
        this.peanutAllergy = false;
        this.category = null;
    }


    public String getProductName(){
        return productName;
    }
    public String getProductCategory(){
        return category;
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


    public String toString(){
        return "Product: " + productName + " Peanut Allergy:" +hasPeanutAllergy() + " Category:" + category;
    }
}