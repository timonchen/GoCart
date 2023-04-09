package comp3350.GoCart.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DealsActivity extends Activity {
    private List<StoreProduct> productsOnDisplay;
    private List<Product> products;
    private BigDecimal[] avgPrices;     // avgPrice at an index represents the average price of a product for a product located at the same index in the products arraylist
    private int currDisplayIndex;

    // Views
    private ImageView prevButton;
    private ImageView nextButton;
    private TextView productName;
    private TextView storeName;
    private TextView storeAddress;
    private TextView avgPrice;
    private TextView salePrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);

        // Find views in activity and assign them to an instance variable
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        productName = findViewById(R.id.productName);
        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        avgPrice = findViewById(R.id.avgPrice);
        salePrice = findViewById(R.id.salePrice);

        productsOnDisplay = new ArrayList<StoreProduct>();

        // Get all products
        AccessProducts accessProducts = new AccessProducts();
        products = accessProducts.getProducts();

        avgPrices = new BigDecimal[products.size()];
        currDisplayIndex = 0;
        prevButton.setVisibility(View.INVISIBLE);

        // Initialize first item on display
        updateProductsDisplay();
        displayProduct();

        // User clicks on the next arrow
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // There are products.size() products that we can display. Prevent user from going beyond that
                prevButton.setVisibility(View.VISIBLE);
                if (currDisplayIndex +1 < products.size()) {
                    currDisplayIndex++;

                    // There are more products to display, so update our list to add it
                    if (currDisplayIndex >= productsOnDisplay.size()){
                        updateProductsDisplay();
                        nextButton.setVisibility(View.VISIBLE);
                    }

                    displayProduct();
                } else {
                    nextButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        // User clicks on the previous arrow
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currDisplayIndex--;
                nextButton.setVisibility(View.VISIBLE);

                if(currDisplayIndex ==0)
                    prevButton.setVisibility(View.INVISIBLE);

                if (currDisplayIndex >= 0) {
                    displayProduct();
                }
                else    // Can't have negative index, set it back to 0
                    currDisplayIndex = 0;
            }
        });
    }

    // Display product and store information of StoreProduct from productOnDisplay at index currDisplayIndex
    private void displayProduct() {
        StoreProduct productToDisplay = productsOnDisplay.get(currDisplayIndex);

        // Get store that sells this product
        AccessStores accessStores = new AccessStores();
        Store store = accessStores.getStoreByID(productToDisplay.getStoreId());

        // Change views in Activity to match product and store's information
        storeName.setText(store.getStoreName());
        storeAddress.setText(store.getStoreAddress());
        productName.setText(productToDisplay.getProductName());
        salePrice.setText("$" + productToDisplay.getPrice().toString());
        avgPrice.setText("$" + avgPrices[currDisplayIndex].toString());
    }

    /* Only call this method if currDisplayIndex >= productsOnDisplay.length
     * This method updates the list of products on display by adding a new StoreProduct on productsOnDisplay
     * It uses product at index currDisplayIndex from products list, and finds an appropriate StoreProduct equivalent which gets appended to the end of productsOnDisp[lay
     */
    private void updateProductsDisplay() {
        Product newProduct = products.get(currDisplayIndex);
        AccessStoreProduct accessStoreProduct = new AccessStoreProduct();

        // Get the average price for product
        avgPrices[currDisplayIndex] = accessStoreProduct.findAveragePrice(newProduct);

        // Add the cheapest StoreProduct that contains the product to the list
        productsOnDisplay.add(accessStoreProduct.findCheapestStore(newProduct));
    }

}
