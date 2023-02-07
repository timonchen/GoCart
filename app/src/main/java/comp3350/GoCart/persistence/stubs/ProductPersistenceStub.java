package comp3350.GoCart.persistence.stubs;

import java.math.BigDecimal;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class ProductPersistenceStub implements ProductPersistence{

    private final List<Product> products;


    public ProductPersistenceStub(){
        products = new ArrayList<>();

        products.add(new Product("Banana", new BigDecimal("1.05"),false));
        products.add(new Product("Rye Bread", new BigDecimal("1.45"),true));
        products.add(new Product("Lucky Charms", new BigDecimal("3.99"),true));
        products.add(new Product("Beef Jerkey", new BigDecimal("9.99"),false));
        products.add(new Product("12 cookies", new BigDecimal("12.99"),true));
        products.add(new Product("toilet paper", new BigDecimal("15.99"),false));

    }

    // int or store, store seems awkward
    @Override
    public List<Product> getProduct(Product currentProduct) {
        int index = products.indexOf(currentProduct);
        List<Product> toReturn = new ArrayList<>();
        if(index > -1) {
            toReturn.add(products.get(index));
        }

        return toReturn;
    }

    @Override
    public List<Product> getDietaryRestrictedProducts() {
        List<Product> restrictedProducts= new ArrayList<>();

        for(int i=0;i<products.size();i++){
            if(!products.get(i).hasPeanutAllergy()){
                restrictedProducts.add(products.get(i));
            }
        }
        return restrictedProducts;

    }

    @Override
    public Product insertProduct(Product toInsert) {
        products.add(toInsert);
        return toInsert;
    }

    @Override
    public Product updateProduct(Product toUpdate) {
        int index = products.indexOf(toUpdate);
        if(index > -1) {
            products.set(index, toUpdate);
        }
        return toUpdate;
    }

    @Override
    public boolean deleteProduct(Product toDelete) {
        return products.remove(toDelete);
    }
}