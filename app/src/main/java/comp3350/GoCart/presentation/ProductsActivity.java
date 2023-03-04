package comp3350.GoCart.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

public class ProductsActivity extends Activity {

    private ImageView storeImage;
    private TextView storeName;
    private TextView storeAddress;
    List<Product> storeProducts;
    private SearchView searchBar;
    private RecyclerView productsRecView;
    private ProductsRecViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        storeImage = findViewById(R.id.storeImage);
        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        searchBar = findViewById(R.id.searchBar);
        productsRecView = findViewById(R.id.productsRecView);

        // Get store data from previous page
        Store store = getIntent().getParcelableExtra("selected_store"); // Store products are from
        storeProducts = store.getStoreProducts();

        // Set data in views to store data
        storeName.setText(store.getStoreName());
        storeAddress.setText(store.getStoreAddress());

        // Initialize Recycler View adapter
        adapter = new ProductsRecViewAdapter();
        adapter.setProducts(storeProducts);
        productsRecView.setAdapter(adapter);

        // Code related to search bar functionality
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateProductList(newText);
                return false;
            }
        });

        // Recycler view will use a grid layout with 2 columns per row
        productsRecView.setLayoutManager(new GridLayoutManager(this, 2));
        productsRecView.setFocusable(false);
    }

    private void updateProductList(String productName) {
        List<Product> productList = new ArrayList<>();

        if (productName.equals("")) {   // If query empty, display everything
            productList = storeProducts;
        }
        else {
            for (int i = 0; i < storeProducts.size(); i++) {
                Product product = storeProducts.get(i);
                if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                    productList.add(product);
                }
            }
        }


        // Update recycler view
        adapter.setProducts(productList);
        productsRecView.setAdapter(adapter);
    }
}