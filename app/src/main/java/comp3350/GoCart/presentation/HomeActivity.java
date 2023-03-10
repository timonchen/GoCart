package comp3350.GoCart.presentation;

import comp3350.GoCart.R;
import comp3350.GoCart.application.Main;
import android.app.Activity;
import android.content.Context;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;

public class HomeActivity extends Activity {

    // Codes for activity returns
    public static final int REQUEST_LOGIN = 1;
    public static final String EXTRA_USER = "loggedInUser";

    private User loggedInUser;
    private boolean isLoggedIn;
    private Button loginButton;
    private Button userAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        copyDatabaseToDevice();
        this.loggedInUser = null;
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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);


                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();

        }
    }

    public void buttonLoginPageOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        usersIntent.putExtra(UsersActivity.EXTRA_PAGE_TYPE, UsersActivity.PAGE_TYPE_LOGIN);
        HomeActivity.this.startActivityForResult(usersIntent, REQUEST_LOGIN);    // startActivityForResult expects that something will be returned by the activity
    }

    public void buttonUserAccountOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        usersIntent.putExtra(UsersActivity.EXTRA_USER, loggedInUser);
        usersIntent.putExtra(UsersActivity.EXTRA_PAGE_TYPE, UsersActivity.PAGE_TYPE_USER_ACCOUNT);
        HomeActivity.this.startActivity(usersIntent);
    }

    // This method deals with returns made by another activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            isLoggedIn = data.getBooleanExtra("loggedInStatus", false);  // defaultValue (false) is used if no result with key "loggedInStatus" was returned
            loggedInUser = data.getParcelableExtra(EXTRA_USER);
            if (loggedInUser != null)
            {
                System.out.println("User = " + loggedInUser.getInitials());
                Button loginButton = (Button) findViewById(R.id.loginButton);
                Button userAccountButton = (Button) findViewById(R.id.userAccountButton);

                loginButton.setVisibility(View.GONE);
                userAccountButton.setVisibility(View.VISIBLE);
                userAccountButton.setText(loggedInUser.getInitials());
                isLoggedIn = true;
            }
            else {  // null means user was logged out
                isLoggedIn = false;
            }
            updateActivity();
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
