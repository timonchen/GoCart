package comp3350.GoCart.presentation;

import android.app.Activity;
import android.os.Bundle;

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


}
