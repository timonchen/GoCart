package comp3350.gocart.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

import comp3350.gocart.objects.product;
import comp3350.gocart.persistence.productPersistence;

public class productPersistenceStub implements productPersistence{

    // dont need hashmap with index
    ** private ArrayList<ArrayList<Product>>  products;
    

    /

    public productPersistenceStub(){
        this.products = new Arraylist<ArrayList<Product>>();
        //store 1
        ArrayList<Product> s1 = new ArrayList<product>();
        //HashMap<Product> s1 = new HashMap<product>();
        s1.add(new Product("apple",2))
        s1.add(new Product("bannana",1.8))
 
        this.products.add(s1);


        //store 2
        ArrayList<Product> s2 = new ArrayList<product>();
        s2.add(new Product("apple",2.1))

        this.products.add(s2);
    }

    // int or store, store seems awkward
    public List<Product> getProduct<int currentStore, Product currentProduct{
        final List<Product> product = new ArrayList<>();
        product.add(this.products.get(currentStore).get(currentProduct.getProductIndex()));
    
    {

    }




}