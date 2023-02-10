package comp3350.GoCart.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.R;

import java.util.List;

public class FindStoreByNameActivity extends Activity {

    // variables for UI elements (view)
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView storesRecView;

    private AccessStores accessStores;
    private List<Store> storeList;

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
        storeList = accessStores.getStores();

        // Connect recView adapter to the recView in activity_find_store
        StoresRecViewAdapter adapter = new StoresRecViewAdapter();
        storesRecView.setLayoutManager(new LinearLayoutManager(this));

        // Listen for "search button" press
        // When button press detected, find a store with a matching name
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = searchBar.getText().toString();  // Get the text from the text box
                List<Store> searchResults = accessStores.getStoresByName(input);

                // Display search results in the recycler viewer
                adapter.setStores(searchResults);
                storesRecView.setAdapter(adapter);
            }
        });

    }


}
