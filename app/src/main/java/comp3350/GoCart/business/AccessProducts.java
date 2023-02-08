package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProducts{

    private final ProductPersistence productPersistence;

    private List<Product> products; // list of stores of products.
    
    private final Product product;
    private final int currentProduct;


    public AccessProducts(){
        productPersistence = Services.getProductPersistence();
        products = null;
        product = null;
        currentProduct = 0;
        
    }
    public List<Product> getDietaryProducts()
    {
        products = productPersistence.getDietaryRestrictedProducts();
        return Collections.unmodifiableList(products);
    }

    public List<Product> searchProductsByName(String productName) {
        products = productPersistence.searchProductsByName(productName);
        return Collections.unmodifiableList(products);
    }
}
