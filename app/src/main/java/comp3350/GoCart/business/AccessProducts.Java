package comp3350.gocart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProdutcs{

    private ProductPersistence productPersistence;

    **private List<List<Product>> products; // list of stores of products. 
    
    private Product product;
    private int currentProduct;


    public AccessProdutcs(){
        productPersistence = Services.getProductPersistence();
        products = null;
        product = null;
        currentProduct = 0;
        
    }
}
