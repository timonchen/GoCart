package comp3350.GoCart.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import comp3350.GoCart.R;

public class FindStoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_store);
    }

    public void findStoreNameOnClick(View v) {
        Intent storeNameIntent = new Intent(FindStoreActivity.this, FindStoreByNameActivity.class);
        FindStoreActivity.this.startActivity(storeNameIntent);
    }

    public void findStoreClosestOnClick(View v) {
        Intent storeClosestIntent = new Intent(FindStoreActivity.this, FindClosetStoreActivity.class);
        FindStoreActivity.this.startActivity(storeClosestIntent);
    }


}
