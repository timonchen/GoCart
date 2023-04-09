package comp3350.GoCart.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.GoCart.application.Services;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class AccessProducts{

    private ProductPersistence productPersistence;

    private List<Product> products;
    private List<String> categories;


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
    public List<Product> productCatogory(String category) {
        products = productPersistence.productCategory(category);
        return Collections.unmodifiableList(products);
    }

    public List<String> getAllCategories(){
        products = productPersistence.getAllProducts();
        categories = new ArrayList<>();
        for(int i=0; i< products.size();i++){
    if(!categories.contains(products.get(i).getProductCategory())){
        categories.add(products.get(i).getProductCategory());
    }
        }
        return categories;
    }

}
