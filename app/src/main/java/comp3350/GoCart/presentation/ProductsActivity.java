package comp3350.GoCart.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

public class ProductsActivity extends Activity {
    final String DEFAULT_CATEGORY = "All";  // Category when no category is selected

    // UI Elements (views)
    private TextView storeName;
    private TextView storeAddress;
    private SearchView searchBar;
    private RecyclerView productsRecView;
    private ProductsRecViewAdapter adapter;
    private Spinner categorySpinner;

    // Instance variables
    private AccessStoreProduct accessStoreProduct;
    List<StoreProduct> storeProducts;
    private String storeID;
    private String lastSearch;  // Last known search made by the user
    private boolean hasAllergen;
    private FloatingActionButton viewCart;
    private ShoppingCart shoppingCart;
    private String currCategory;
    private AccessStores accessStores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Initialize views
        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        searchBar = findViewById(R.id.searchBar);
        productsRecView = findViewById(R.id.productsRecView);
        viewCart = findViewById(R.id.cart_fab);
        categorySpinner = findViewById(R.id.categorySpinner);
        Switch allergenSwitch = findViewById(R.id.allergenSwitch);

        // Initialize instance variables
        lastSearch = "";
        currCategory = DEFAULT_CATEGORY;
        accessStores = new AccessStores();

        // Get store data from previous activity
        storeID = getIntent().getStringExtra("selectedStoreID");
        Store store = accessStores.getStoreByID(storeID);

        // Get Store's products
        accessStoreProduct = new AccessStoreProduct();
        storeProducts = accessStoreProduct.getStoresProducts(storeID);

        ShoppingCart.getInstance().setStore(store);

        // Set data in views to store data
        storeName.setText(store.getStoreName());
        storeAddress.setText(store.getStoreAddress());

        // Initialize Recycler View adapter
        adapter = new ProductsRecViewAdapter();
        adapter.setProducts(storeProducts);
        productsRecView.setAdapter(adapter);

        // Initialize Category spinner adapter
        AccessProducts accessProducts = new AccessProducts();
        List<String> categoriesList = accessProducts.getAllCategories();
        categoriesList.add(0, DEFAULT_CATEGORY);    // Add the default value to the top of the list so that it can be selected in the spinner
        String[] allCategories = categoriesList.toArray(new String[0]);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, allCategories);
        categorySpinner.setAdapter(spinnerAdapter);

        viewCart.setOnClickListener(view -> {
            Intent intent = new Intent(ProductsActivity.this, ShoppingCartActivity.class);
            startActivityForResult(intent,1);
        });

        // Code related to search bar functionality
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Runs when user hits enter. This method is redundant in this application, so it is left empty
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateProductList(query);
                return false;
            }

            // Runs whenever the user types a new word or deletes a word in the search bar
            @Override
            public boolean onQueryTextChange(String newText) {
                updateProductList(newText);
                return false;
            }
        });

        // The following code is run when the allergen switch is clicked
        allergenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            // Runs whenever the switch changes state
            // isChecked = false if the switch is off, otherwise it is true
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hasAllergen = isChecked;
                updateProductList(lastSearch);
            }
        });

        // The following code is run whenever an interaction with the category spinner occurs
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currCategory = parent.getItemAtPosition(position).toString();
                updateProductList(lastSearch);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currCategory = DEFAULT_CATEGORY;
                updateProductList(lastSearch);
            }
        });

        // Recycler view will use a grid layout with 2 columns per row
        productsRecView.setLayoutManager(new GridLayoutManager(this, 2));
        productsRecView.setFocusable(false);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                storeID = data.getStringExtra("newStoreID");
                Store store = accessStores.getStoreByID(storeID);
                storeName.setText(store.getStoreName());
                storeAddress.setText(store.getStoreAddress());
                storeProducts = accessStoreProduct.getStoresProducts(storeID);
                adapter.setProducts(storeProducts);
            }
        }
    }

    // This method uses the adaptor to change the products being displayed
    private void updateProductList(String productName) {
        lastSearch = productName;

        if (hasAllergen && currCategory.equals(DEFAULT_CATEGORY))   // Search has allergen and no category
            adapter.setProducts(accessStoreProduct.getStoreProductsByNameWithAllergen(storeID, productName));
        else if (hasAllergen && !currCategory.equals(DEFAULT_CATEGORY)) // Search has allergen and a category
            adapter.setProducts(accessStoreProduct.getStoreProductsByNameWithAllergen(storeID, productName, currCategory));
        else if (!hasAllergen && currCategory.equals(DEFAULT_CATEGORY)) // Search has no allergen and no category
            adapter.setProducts(accessStoreProduct.getStoreProductsByName(storeID, productName));
        else // Search has no allergen but has a category
            adapter.setProducts(accessStoreProduct.getStoreProductsByName(storeID, productName, currCategory));
        productsRecView.setAdapter(adapter);
    }
}
