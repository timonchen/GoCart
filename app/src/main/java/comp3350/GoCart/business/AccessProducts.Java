package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProducts{

    private ProductPersistence productPersistence;

    private List<Product> products; // list of stores of products.
    
    private Product product;
    private int currentProduct;


    public AccessProducts(){
        productPersistence = Services.getProductPersistence();
        products = null;
        product = null;
        currentProduct = 0;
        
    }
}
