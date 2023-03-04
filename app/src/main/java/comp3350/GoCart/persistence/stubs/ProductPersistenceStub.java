package comp3350.GoCart.persistence.stubs;

import java.math.BigDecimal;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.ProductPersistence;

public class ProductPersistenceStub implements ProductPersistence{

    private List<Product> products;


    public ProductPersistenceStub(){
        products = new ArrayList<>();

        products.add(new Product( "4521","Banana",false));
        products.add(new Product( "6849","Rye Bread", true));
        products.add(new Product( "6917","Whole Wheat Bread", true));
        products.add(new Product( "3818","Lucky Charms",true));
        products.add(new Product( "1953","Beef Jerkey", false));
        products.add(new Product( "1958","12 cookies", true));
        products.add(new Product( "3984","toilet paper", false));
        products.add(new Product( "3846","Ice-cream", false));
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
    // Method to return a list of all the products matching the productName that is passed as parameter
    public List<Product> searchProductsByName(String productName) {
        List<Product> matchingProducts = new ArrayList<>();

        if (productName != null)
        {
            for (int i = 0; i < products.size(); i++)
            {
                if (products.get(i).getProductName().toLowerCase().contains(productName.toLowerCase()))     //case should not affect the search process
                {
                    matchingProducts.add(products.get(i));
                }
            }
        }

        return matchingProducts;
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }
    /*
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
    */

}