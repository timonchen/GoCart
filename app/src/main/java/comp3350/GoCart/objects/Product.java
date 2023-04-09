package comp3350.GoCart.objects;


import java.util.Objects;

public class Product {

    private String productID;
    private String productName;
    private String category;


    private  boolean peanutAllergy;

    private Product() {}
    public Product(final String newProductID, final String newProductName, boolean peanutAllergy, final String category){
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

    public static class ProductBuilder {
        private Product product;

        public ProductBuilder() {
            this.product = new Product();
        }

        public ProductBuilder productID(String productID) {
            this.product.productID = productID;
            return this;
        }
        public ProductBuilder productName(String productName) {
            this.product.productName = productName;
            return this;
        }
        public ProductBuilder category(String category) {
            this.product.category = category;
            return this;
        }

        public ProductBuilder peanutAllergy(boolean peanutAllergy) {
            this.product.peanutAllergy = peanutAllergy;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}