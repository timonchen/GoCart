package comp3350.GoCart.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.GoCart.R;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void buttonFindStoreOnClick(View v){
        Intent storesIntent = new Intent(HomeActivity.this, FindStoreActivity.class);
        HomeActivity.this.startActivity(storesIntent);
    }

    public void buttonShoppingCartOnClick(View v){
        Intent cartIntent = new Intent(HomeActivity.this, ShoppingCartActivity.class);
        HomeActivity.this.startActivity(cartIntent);
    }
}
