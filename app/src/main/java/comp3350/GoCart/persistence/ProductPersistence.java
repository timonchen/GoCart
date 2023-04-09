package comp3350.GoCart.persistence;

import java.util.List;

import comp3350.GoCart.objects.Product;

public interface ProductPersistence {

    //likely wont be needed
    List<Product> getDietaryRestrictedProducts();

    List<Product> searchProductsByName(String productName);

    List<Product> getAllProducts();

    List<Product> productCategory(String category) ;
}