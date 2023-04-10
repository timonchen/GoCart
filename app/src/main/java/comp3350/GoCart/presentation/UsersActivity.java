package comp3350.GoCart.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.User;

public class UsersActivity extends Activity
{
    // Codes to send back to home activity

    public static final int PAGE_TYPE_LOGIN = 1;
    public static final int PAGE_TYPE_USER_ACCOUNT = 2;
    // constants for parcel
    public static final String EXTRA_USER_EMAIL = "loggedInUserEmail";
    public static final String EXTRA_USER_PASSWORD = "loggedInUserPassword";
    public static final String EXTRA_PAGE_TYPE = "pageType";

    private AccessUsers accessUsers;
    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        accessUsers = new AccessUsers();

        int pageType = getIntent().getIntExtra(EXTRA_PAGE_TYPE, PAGE_TYPE_LOGIN);

        if (pageType == PAGE_TYPE_LOGIN)
        {
            setContentView(R.layout.activity_login);
        }
        else if (pageType == PAGE_TYPE_USER_ACCOUNT)
        {
            setContentView(R.layout.activity_user_account);

            // Retrieve logged in user data from other activity
            String loggedInUserEmail = getIntent().getStringExtra(EXTRA_USER_EMAIL);
            String loggedInUserPassword = getIntent().getStringExtra(EXTRA_USER_PASSWORD);
            loggedInUser = accessUsers.getUser(loggedInUserEmail, loggedInUserPassword);

            TextView userName = (TextView) findViewById(R.id.textViewUserName);
            userName.setText(loggedInUser.getName());
            
            TextView userPhone = (TextView) findViewById(R.id.textViewUserPhone);

            TextView userEmail = (TextView) findViewById(R.id.textViewUserEmail);
            userEmail.setText(loggedInUser.getEmail());
        }
    }

    public void buttonSignUpPageOnClick(View v)
    {
        setContentView(R.layout.activity_signup);
    }

    public void buttonUserSignUpOnClick(View v)
    {
        User newUser = createUserFromEditText();

        if (newUser != null)
        {
            loggedInUser = newUser;

            // Inform home activity that the user has logged in
            Intent result = new Intent();
            result.putExtra(EXTRA_USER_EMAIL, loggedInUser.getEmail());
            result.putExtra(EXTRA_USER_PASSWORD, loggedInUser.getPassword());
            setResult(RESULT_OK, result);

            finish();
        }
    }

    public void buttonLoginOnClick(View view)
    {
        String email = null;
        String password = null;
        boolean valid = true;
        ShoppingCart.getInstance().clearCart();

        EditText editEmail = (EditText)findViewById(R.id.editLoginEmail);
        if (!TextUtils.isEmpty(editEmail.getText().toString())) {
            email = editEmail.getText().toString();
            if (!email.contains("@") || !email.contains("."))
            {
                email = null;
                valid = false;
                editEmail.setError("Not a valid email");
            }
        } else {
            editEmail.setError("Email cannot be empty");
            valid = false;
        }

        EditText editPassword = (EditText)findViewById(R.id.editLoginPassword);
        if (!TextUtils.isEmpty(editPassword.getText().toString())) {
            password = editPassword.getText().toString();
        } else {
            editPassword.setError("Password cannot be empty");
            valid = false;
        }

        if (valid)
        {
            User user = accessUsers.getUser(email, password);

            if (user == null)
            {
                String warning = "Warning";
                String message = "Invalid email/password";
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();

                alertDialog.setTitle(warning);
                alertDialog.setMessage(message);

                alertDialog.show();
            }
            else
            {
                setContentView(R.layout.activity_home);

                loggedInUser = user;

                // Inform home activity that the user has logged in
                Intent result = new Intent();
                result.putExtra(EXTRA_USER_EMAIL, loggedInUser.getEmail());
                result.putExtra(EXTRA_USER_PASSWORD, loggedInUser.getPassword());
                setResult(RESULT_OK, result);

                finish();
            }
        }
    }

    private User createUserFromEditText()
    {
        User newUser = null;

        boolean valid = true;
        String firstName = null;
        String lastName = null;
        String address = null;
        String city = null;
        String province = null;
        String zipCode = null;
        int phone = 0;
        String phoneString = null;
        String email = null;
        String password = null;
        String confirmPassword = null;

        EditText editFirstName = (EditText)findViewById(R.id.editFirstName);
        EditText editLastName = (EditText) findViewById(R.id.editLastName);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        EditText editCity = (EditText) findViewById(R.id.editCity);
        EditText editProvince = (EditText)findViewById(R.id.editProvince);
        EditText editZipCode = (EditText)findViewById(R.id.editZipCode);
        EditText editPhone = (EditText) findViewById(R.id.editPhone);
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        EditText editConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        address = editAddress.getText().toString();
        city = editCity.getText().toString();
        province = editProvince.getText().toString();
        zipCode = editZipCode.getText().toString();
        phoneString = editPhone.getText().toString();
        email = editEmail.getText().toString();
        password = editPassword.getText().toString();
        confirmPassword = editConfirmPassword.getText().toString();

        newUser = accessUsers.addUser(firstName, lastName, address, city, province, zipCode, phoneString, email, password, confirmPassword);

        if (newUser == null)
        {
            if (firstName.isEmpty()) {
                editFirstName.setError("First name cannot be empty");
            }

            if (lastName.isEmpty()) {
                editLastName.setError("Last name cannot be empty");
            }

            if (address.isEmpty()) {
                editAddress.setError("Address cannot be empty");
            }

            if (city.isEmpty()) {
                editCity.setError("City cannot be empty");
            }

            if (province.isEmpty()) {
                editProvince.setError("Province cannot be empty");
            }

            if (!zipCode.isEmpty()) {
                if (zipCode.length() != 6) {
                    editZipCode.setError("Postal Code must be of length 6");
                }
            } else {
                editZipCode.setError("Zip Code cannot be empty");
            }

            if (!phoneString.isEmpty()) {
                try {
                    phone = Integer.parseInt(editPhone.getText().toString());
                    if (phoneString.length() != 10) {
                        editPhone.setError("Phone must contain 10 digits.");
                    }
                } catch (NumberFormatException e) {
                    editPhone.setError("Phone can only contain numbers.");
                }
            } else {
                editPhone.setError("Phone cannot be empty");
            }

            if (!email.isEmpty()) {
                if (!email.contains("@") || !email.contains(".")) {
                    editEmail.setError("Not a valid email");
                }
            } else {
                editEmail.setError("Email cannot be empty");
            }

            if (!password.isEmpty()) {
                if (password.length() < 6) {
                    editPassword.setError("Password must contain at least 6 characters");
                }
            } else {
                editPassword.setError("Password cannot be empty");
            }

            if (!confirmPassword.isEmpty()) {
                if (!password.equals(confirmPassword)) {
                    editConfirmPassword.setError("Password does not match");
                }
            } else {
                editConfirmPassword.setError("Cannot be empty");
            }
        }

        return newUser;
    }

    public void buttonInitializeUpdateUserNameOnClick(View v)
    {
        TextView textView = (TextView) findViewById(R.id.textViewUserName);
        EditText editText = (EditText) findViewById(R.id.editViewUserName);
        editText.setText(loggedInUser.getName());
        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);

        Button initializeUpdateNameButton = (Button) findViewById(R.id.buttonInitializeUpdateName);
        Button completeUpdateNameButton = (Button) findViewById(R.id.buttonCompleteUpdateName);

        initializeUpdateNameButton.setVisibility(View.GONE);
        completeUpdateNameButton.setVisibility(View.VISIBLE);

        TextView textViewPhone = (TextView) findViewById(R.id.textViewUserPhone);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textViewPhone.getLayoutParams();
        layoutParams.topToBottom = R.id.editViewUserName;
        textViewPhone.setLayoutParams(layoutParams);

        Button initializeUpdatePhone = (Button) findViewById(R.id.buttonInitializeUpdatePhone);
        layoutParams = (ConstraintLayout.LayoutParams) initializeUpdatePhone.getLayoutParams();
        layoutParams.topToBottom = R.id.buttonCompleteUpdateName;
        initializeUpdatePhone.setLayoutParams(layoutParams);
    }

    public void buttonCompleteUpdateUserNameOnClick(View v)
    {
        EditText editName = (EditText) findViewById(R.id.editViewUserName);
        String name;
        if (!TextUtils.isEmpty(editName.getText().toString())) {
            name = editName.getText().toString();
            loggedInUser.updateName(name);
            accessUsers.updateUser(loggedInUser);
            TextView textView = (TextView) findViewById(R.id.textViewUserName);
            textView.setText(loggedInUser.getName());
            textView.setVisibility(View.VISIBLE);
            editName.setVisibility(View.GONE);

            TextView textViewPhone = (TextView) findViewById(R.id.textViewUserPhone);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textViewPhone.getLayoutParams();
            layoutParams.topToBottom = R.id.textViewUserName;
            textViewPhone.setLayoutParams(layoutParams);

            Button initializeUpdateNameButton = (Button) findViewById(R.id.buttonInitializeUpdateName);
            Button completeUpdateNameButton = (Button) findViewById(R.id.buttonCompleteUpdateName);

            initializeUpdateNameButton.setVisibility(View.VISIBLE);
            completeUpdateNameButton.setVisibility(View.GONE);

            Button initializeUpdatePhone = (Button) findViewById(R.id.buttonInitializeUpdatePhone);
            layoutParams = (ConstraintLayout.LayoutParams) initializeUpdatePhone.getLayoutParams();
            layoutParams.topToBottom = R.id.buttonInitializeUpdateName;
            initializeUpdatePhone.setLayoutParams(layoutParams);
        }
        else {
            editName.setError("Name cannot be empty");
        }
    }

    public void buttonInitializeUpdateUserPhoneOnClick(View v) {
        TextView textView = (TextView) findViewById(R.id.textViewUserPhone);
        EditText editText = (EditText) findViewById(R.id.editViewUserPhone);
        editText.setText(loggedInUser.getPhone() + "");
        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);

        Button initializeUpdatePhoneButton = (Button) findViewById(R.id.buttonInitializeUpdatePhone);
        Button completeUpdatePhoneButton = (Button) findViewById(R.id.buttonCompleteUpdatePhone);

        initializeUpdatePhoneButton.setVisibility(View.GONE);
        completeUpdatePhoneButton.setVisibility(View.VISIBLE);

        TextView textViewEmail = (TextView) findViewById(R.id.textViewUserEmail);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textViewEmail.getLayoutParams();
        layoutParams.topToBottom = R.id.editViewUserPhone;
        textViewEmail.setLayoutParams(layoutParams);

        Button initializeUpdateEmail = (Button) findViewById(R.id.buttonInitializeUpdateEmail);
        layoutParams = (ConstraintLayout.LayoutParams) initializeUpdateEmail.getLayoutParams();
        layoutParams.topToBottom = R.id.buttonCompleteUpdatePhone;
        initializeUpdateEmail.setLayoutParams(layoutParams);
    }

    public void buttonCompleteUpdateUserPhoneOnClick(View v)
    {
        EditText editPhone = (EditText) findViewById(R.id.editViewUserPhone);
        int phone = 0;
        if (!TextUtils.isEmpty(editPhone.getText().toString())) {
            try {
                phone = Integer.parseInt(editPhone.getText().toString());
                if (editPhone.getText().toString().length() != 10)
                {
                    editPhone.setError("Phone must contain 10 digits.");
                }
                else {

                    loggedInUser.updatePhone(phone);
                    accessUsers.updateUser(loggedInUser);

                    TextView textView = (TextView) findViewById(R.id.textViewUserPhone);
                    textView.setText(loggedInUser.getPhone() + "");
                    textView.setVisibility(View.VISIBLE);
                    editPhone.setVisibility(View.GONE);

                    TextView textViewEmail = (TextView) findViewById(R.id.textViewUserEmail);
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textViewEmail.getLayoutParams();
                    layoutParams.topToBottom = R.id.textViewUserPhone;
                    textViewEmail.setLayoutParams(layoutParams);

                    Button initializeUpdatePhoneButton = (Button) findViewById(R.id.buttonInitializeUpdatePhone);
                    Button completeUpdatePhoneButton = (Button) findViewById(R.id.buttonCompleteUpdatePhone);

                    initializeUpdatePhoneButton.setVisibility(View.VISIBLE);
                    completeUpdatePhoneButton.setVisibility(View.GONE);

                    Button initializeUpdateEmail = (Button) findViewById(R.id.buttonInitializeUpdateEmail);
                    layoutParams = (ConstraintLayout.LayoutParams) initializeUpdateEmail.getLayoutParams();
                    layoutParams.topToBottom = R.id.buttonInitializeUpdatePhone;
                    initializeUpdateEmail.setLayoutParams(layoutParams);
                }
            }
            catch (NumberFormatException e)
            {
                editPhone.setError("Phone can only contain numbers.");
            }

        }
        else {
            editPhone.setError("Phone cannot be empty");
        }
    }


    public void buttonInitializeUpdateUserEmailOnClick(View v)
    {
        TextView textView = (TextView) findViewById(R.id.textViewUserEmail);
        EditText editText = (EditText) findViewById(R.id.editViewUserEmail);
        editText.setText(loggedInUser.getEmail());
        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);

        Button initializeUpdateEmailButton = (Button) findViewById(R.id.buttonInitializeUpdateEmail);
        Button completeUpdateEmailButton = (Button) findViewById(R.id.buttonCompleteUpdateEmail);

        initializeUpdateEmailButton.setVisibility(View.GONE);
        completeUpdateEmailButton.setVisibility(View.VISIBLE);
    }

    public void buttonCompleteUpdateUserEmailOnClick(View v)
    {
        EditText editEmail = (EditText) findViewById(R.id.editViewUserEmail);
        String email;
        if (!TextUtils.isEmpty(editEmail.getText().toString())) {
            email = editEmail.getText().toString();
            if (!email.contains("@") || !email.contains("."))
            {
                editEmail.setError("Not a valid email");
            }
            else {
                loggedInUser.updateEmail(email);
                accessUsers.updateUser(loggedInUser);
                TextView textView = (TextView) findViewById(R.id.textViewUserEmail);
                textView.setText(loggedInUser.getEmail());
                textView.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.GONE);

                Button initializeUpdateEmailButton = (Button) findViewById(R.id.buttonInitializeUpdateEmail);
                Button completeUpdateEmailButton = (Button) findViewById(R.id.buttonCompleteUpdateEmail);

                initializeUpdateEmailButton.setVisibility(View.VISIBLE);
                completeUpdateEmailButton.setVisibility(View.GONE);
            }
        }
        else {
            editEmail.setError("Name cannot be empty");
        }
    }

    public void buttonChangePasswordButtonOnClick(View v)
    {
        setContentView(R.layout.activity_update_user_password);
    }

    public void buttonUpdateUserPasswordOnClick(View v)
    {
        String password = null;
        String confirmPassword;
        boolean valid = true;

        EditText editPassword = (EditText) findViewById(R.id.editTextNewPassword);
        if (!TextUtils.isEmpty(editPassword.getText().toString())) {
            password = editPassword.getText().toString();
            if (password.length() < 6)
            {
                valid = false;
                editPassword.setError("Password must contain at least 6 characters");
            }
        } else {
            editPassword.setError("Password cannot be empty");
            valid = false;
        }

        EditText editConfirmPassword = (EditText) findViewById(R.id.editTextConfirmNewPassword);
        if (!TextUtils.isEmpty(editConfirmPassword.getText().toString())) {
            confirmPassword = editConfirmPassword.getText().toString();

            if (password != null && !password.equals(confirmPassword))
            {
                confirmPassword = null;
                valid = false;
                editConfirmPassword.setError("Password does not match");
            }
        } else {
            editConfirmPassword.setError("Cannot be empty");
            valid = false;
        }

        if (valid)
        {
            loggedInUser.updatePassword(password);
            accessUsers.updateUser(loggedInUser);
            String title = "Password Updated";
            String message = "Enjoy shopping with GoCart!";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(message);
            AlertDialog dialog = builder.create();
            dialog.show();

            setContentView(R.layout.activity_user_account);

            TextView userName = (TextView) findViewById(R.id.textViewUserName);
            userName.setText(loggedInUser.getName());

            TextView userPhone = (TextView) findViewById(R.id.textViewUserPhone);
            userPhone.setText((loggedInUser.getPhone()) + "");

            TextView userEmail = (TextView) findViewById(R.id.textViewUserEmail);
            userEmail.setText(loggedInUser.getEmail());
        }
    }

    public void buttonDeleteUserAccount(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete user account?");
        ShoppingCart cart = ShoppingCart.getInstance();

        // Add the "Yes" button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed, delete
                accessUsers.deleteUser(loggedInUser);
                Intent intent = new Intent(UsersActivity.this, HomeActivity.class);
                UsersActivity.this.startActivity(intent);
                cart.clearCart();
            }
        });
        // Add the "No" button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled, do nothing
            }
        });


        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void buttonLogoutOnClick(View v)
    {
        ShoppingCart cart = ShoppingCart.getInstance();
        Intent result = new Intent();
        result.putExtra(EXTRA_USER_EMAIL, (String) null);
        result.putExtra(EXTRA_USER_PASSWORD, (String) null);
        setResult(RESULT_OK, result);
        cart.clearCart();
        finish();
    }
}
