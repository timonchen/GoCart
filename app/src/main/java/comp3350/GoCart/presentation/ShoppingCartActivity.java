package comp3350.GoCart.presentation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.Product;

public class ShoppingCartActivity extends Activity {



    private RecyclerView productRecView;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        productRecView = findViewById(R.id.recViewProducts);
        returnButton = findViewById(R.id.btnReturnFromCart);






    }

}