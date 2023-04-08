package comp3350.GoCart.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;

public class FindClosetStoreActivity extends Activity implements ClosestStoresRecViewAdapter.OnStoreListener {
    // variables for UI elements (view)
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView storesRecView;
    private ClosestStoresRecViewAdapter adapter;

    private AccessStores accessStores;
    private List<Store> storeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_closest_store);

        // Match UI element variables with UI elements on XML file
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);
        storesRecView = findViewById(R.id.storesRecView);


        // Grab store data
        accessStores = new AccessStores();

        // Connect recView adapter to the recView in activity_find_store
        adapter = new ClosestStoresRecViewAdapter(this);    // We send this because we want to use this file's implementation of onStoreClick
        storesRecView.setLayoutManager(new LinearLayoutManager(this));

        // Listen for "search button" press
        // When button press detected, find a store with a matching name
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = searchBar.getText().toString();  // Get the text from the text box
                List<Store> searchResults = accessStores.getNearestStores(location);

                adapter.setStores(searchResults);
                storesRecView.setAdapter(adapter);

                // Display message if address was not found
                if (adapter.getItemCount() == 0) {
                    Toast toast = Toast.makeText(FindClosetStoreActivity.this, "Address not found", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300);
                    toast.show();
                }
            }
        });
    }

    // This method will redirect the user to a product page of the store that they created
    //
    // StoresRecViewAdapter will call this method when a store was clicked.
    // Where int position is the index of the store in the recycler view
    @Override
    public void onStoreClick(int position) {
        Store storeClicked = adapter.getStores().get(position);

        Intent intent = new Intent(this, ProductsActivity.class);   // Switch from this activity ProductsActivity
        intent.putExtra("selectedStoreID", storeClicked.getStoreID());    // Send storeID to ProductsActivity so that it can find it
        finish();

        startActivity(intent);
    }
}

