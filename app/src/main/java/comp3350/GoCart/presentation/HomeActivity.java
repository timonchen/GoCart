package comp3350.GoCart.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;

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

    public void buttonLoginPageOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        HomeActivity.this.startActivity(usersIntent);
    }

    public void buttonUserAccountOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        HomeActivity.this.startActivity(usersIntent);
    }
}
