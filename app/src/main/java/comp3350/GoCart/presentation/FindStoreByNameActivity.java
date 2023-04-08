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

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.R;

import java.util.List;

public class FindStoreByNameActivity extends Activity implements StoresRecViewAdapter.OnStoreListener {

    // variables for UI elements (view)
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView storesRecView;
    private StoresRecViewAdapter adapter;
    private AccessStores accessStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_store_by_name);

        // Match UI element variables with UI elements on XML file
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);
        storesRecView = findViewById(R.id.storesRecView);

        // Grab store data
        accessStores = new AccessStores();

        // Connect recView adapter to the recView in activity_find_store_by_name
        adapter = new StoresRecViewAdapter(this);
        storesRecView.setLayoutManager(new LinearLayoutManager(this));

        // Listen for "search button" press
        // When button press detected, find a store with a matching name
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = searchBar.getText().toString();  // Get the text from the text box

                List<Store> searchResults = accessStores.getStoresByName(input);    // Find stores in our list with name given

                // Display search results in the recycler viewer
                adapter.setStores(searchResults);
                storesRecView.setAdapter(adapter);

                // Display message if a store was not found
                if (adapter.getItemCount() == 0) {
                    Toast toast = Toast.makeText(FindStoreByNameActivity.this, "Store not found", Toast.LENGTH_SHORT);
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
