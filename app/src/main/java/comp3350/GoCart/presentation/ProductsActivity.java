package comp3350.GoCart.presentation;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;

public class ProductsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Store store = getIntent().getParcelableExtra("selected_store"); // Store products are from

        List<Product> storeProducts = store.getStoreProducts();
    }
}