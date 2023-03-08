package comp3350.GoCart.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.GoCart.R;

public class HomeActivity extends Activity {

    // Codes for activity returns
    private final int REQUEST_IS_LOGGED_IN = 1;

    private boolean isLoggedIn;
    private Button loginButton;
    private Button userAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        isLoggedIn = false;
        loginButton = findViewById(R.id.loginButton);
        userAccountButton = findViewById(R.id.userAccountButton);
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
        HomeActivity.this.startActivityForResult(usersIntent, REQUEST_IS_LOGGED_IN);    // startActivityForResult expects that something will be returned by the activity
    }

    public void buttonUserAccountOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        HomeActivity.this.startActivity(usersIntent);
    }

    // This method deals with returns made by another activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IS_LOGGED_IN) {
            if (resultCode == RESULT_OK) {
                isLoggedIn = data.getBooleanExtra("loggedInStatus", false);  // defaultValue (false) is used if no result with key "loggedInStatus" was returned
                updateActivity();
            }
        }
    }

    private void updateActivity() {
        if (isLoggedIn) {
            loginButton.setVisibility(View.GONE);   // Hide login button
            userAccountButton.setVisibility(View.VISIBLE);  // Display user button

            // Inform user of the update
            String accountCreated = "Account Created";
            String welcomeMessage = "Enjoy shopping with GoCart!";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle(accountCreated);
            alertDialog.setMessage(welcomeMessage);

            alertDialog.show();
        }
    }
}
