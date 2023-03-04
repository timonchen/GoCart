package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProducts{

    private final ProductPersistence productPersistence;

    private List<Product> products; // list of stores of products.
    

    public AccessProducts(){
        productPersistence = Services.getProductPersistence();
        products = null;
    }
    public List<Product> getDietaryProducts()
    {
        products = productPersistence.getDietaryRestrictedProducts();
        return Collections.unmodifiableList(products);
    }

    //Method to search for a product by name.
    //productName: the name of the product passed to the method which has to be searched.
    public List<Product> searchProductsByName(String productName) {
        products = productPersistence.searchProductsByName(productName);
        return Collections.unmodifiableList(products);
    }

    public List<Product> getAllProducts(){
        products = productPersistence.getAllProducts();
        return Collections.unmodifiableList(products);
    }
}
