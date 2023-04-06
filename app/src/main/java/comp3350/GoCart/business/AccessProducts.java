package comp3350.GoCart.business;

import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProducts{

    private ProductPersistence productPersistence;

    private List<Product> products;


    public AccessProducts(){
        productPersistence = Services.getProductPersistence();
        products = null;
    }


    public AccessProducts(final ProductPersistence productPersistence){
        this();
        this.productPersistence = productPersistence;
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

    public List<Product> getProducts() {
        products = productPersistence.getAllProducts();
        return Collections.unmodifiableList(products);
    }
    public List<Product> searchProductsByCategory(String category) {
        products = productPersistence.searchProductsByCategory(category);
        return Collections.unmodifiableList(products);
    }

}
