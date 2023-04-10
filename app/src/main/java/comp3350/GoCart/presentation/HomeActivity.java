package comp3350.GoCart.presentation;

import comp3350.GoCart.R;

import android.app.Activity;
import android.content.Context;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


import comp3350.GoCart.application.Services;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;

public class HomeActivity extends Activity {

    // Codes for activity returns
    public static final int REQUEST_LOGIN = 1;
    public static final String EXTRA_USER = "loggedInUser";

    private User loggedInUser;
    private boolean isLoggedIn;

    private Button userAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button findStoreButton = (Button) findViewById(R.id.findStoreButton);
        findStoreButton.setVisibility(View.GONE);
        Button discoverDealsButton = (Button) findViewById(R.id.discoverDealsButton);
        discoverDealsButton.setVisibility(View.GONE);
        copyDatabaseToDevice();
        this.loggedInUser = null;
        isLoggedIn = false;
        userAccountButton = (Button) findViewById(R.id.userAccountButton);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // Executed when "Find Store" button is clicked
    public void buttonFindStoreOnClick(View v) {
        Intent storesIntent = new Intent(HomeActivity.this, FindStoreActivity.class);
        HomeActivity.this.startActivity(storesIntent);
    }

    // Executed when "Discover Deals" button is clicked
    public void buttonDiscoverDealsOnClick(View v) {
        Intent dealsIntent = new Intent(HomeActivity.this, DealsActivity.class);
        HomeActivity.this.startActivity(dealsIntent);
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

            Services.setDBPathName(dataDirectory.toString() + "/" + Services.getDBPathName());

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

            if (!outFile.exists()) {
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
    }

    public void buttonLoginPageOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        usersIntent.putExtra(UsersActivity.EXTRA_PAGE_TYPE, UsersActivity.PAGE_TYPE_LOGIN);
        HomeActivity.this.startActivityForResult(usersIntent, REQUEST_LOGIN);    // startActivityForResult expects that something will be returned by the activity
    }

    public void buttonUserAccountOnClick(View v) {
        Intent usersIntent = new Intent(HomeActivity.this, UsersActivity.class);
        usersIntent.putExtra(UsersActivity.EXTRA_USER_EMAIL, loggedInUser.getEmail());
        usersIntent.putExtra(UsersActivity.EXTRA_USER_PASSWORD, loggedInUser.getPassword());
        usersIntent.putExtra(UsersActivity.EXTRA_PAGE_TYPE, UsersActivity.PAGE_TYPE_USER_ACCOUNT);
        HomeActivity.this.startActivityForResult(usersIntent, REQUEST_LOGIN);
    }

    // This method deals with returns made by another activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            isLoggedIn = data.getBooleanExtra("loggedInStatus", false);  // defaultValue (false) is used if no result with key "loggedInStatus" was returned

            // Get user info from activity
            String loggedInUserEmail = data.getStringExtra(UsersActivity.EXTRA_USER_EMAIL);
            String loggedInUserPassword = data.getStringExtra(UsersActivity.EXTRA_USER_PASSWORD);

            // Retrieve user from the information
            AccessUsers accessUsers = new AccessUsers();
            loggedInUser = accessUsers.getUser(loggedInUserEmail, loggedInUserPassword);

            Button loginButtonOnStart = (Button) findViewById(R.id.loginButtonOnStart);
            Button userAccountButton = (Button) findViewById(R.id.userAccountButton);
            Button discoverDealsButton = (Button) findViewById(R.id.discoverDealsButton);
            Button findStoreButton = (Button) findViewById(R.id.findStoreButton);
            if (loggedInUser == null)
            {

                loggedInUser = null;
                loginButtonOnStart.setVisibility(View.VISIBLE);
                userAccountButton.setVisibility(View.GONE);
                findStoreButton.setVisibility(View.GONE);
                discoverDealsButton.setVisibility(View.GONE);
                isLoggedIn = false;
            }
            else {
                loginButtonOnStart.setVisibility(View.GONE);
                findStoreButton.setVisibility(View.VISIBLE);
                discoverDealsButton.setVisibility(View.VISIBLE);
                userAccountButton.setVisibility(View.VISIBLE);
                userAccountButton.setText(loggedInUser.getInitials());
                isLoggedIn = true;
            }
            updateActivity();
        }
    }

    // Updates what is displayed in the activity based on if user is logged ins
    private void updateActivity() {
        if (isLoggedIn) {

            userAccountButton.setVisibility(View.VISIBLE);  // Display user button

            // Inform user of the update
            String welcomeMessage = "Enjoy shopping with GoCart!";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setMessage(welcomeMessage);

            alertDialog.show();
        }
        else {

            userAccountButton.setVisibility(View.GONE);

            // Inform user of the update
            String status = "Logged out";
            String exitMessage = "Thank you for shopping with GoCart!";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle(status);
            alertDialog.setMessage(exitMessage);

            alertDialog.show();
        }
    }
}
