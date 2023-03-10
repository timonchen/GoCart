package comp3350.GoCart.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;

public class UsersActivity extends Activity
{
    // Codes to send back to home activity

    public static final int PAGE_TYPE_LOGIN = 1;
    public static final int PAGE_TYPE_USER_ACCOUNT = 2;
    public static final String EXTRA_USER = "loggedInUser";
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
            loggedInUser = getIntent().getParcelableExtra(EXTRA_USER);
            TextView userName = (TextView) findViewById(R.id.textViewUserName);
            userName.setText(loggedInUser.getName());
            
            TextView userPhone = (TextView) findViewById(R.id.textViewUserPhone);
            userPhone.setText((loggedInUser.getPhone()) + "");

            TextView userEmail = (TextView) findViewById(R.id.textViewUserEmail);
            System.out.println("            TextView userEmail = (TextView) findViewById(R.id.textViewUserEmail);\n");
            System.out.println("user email: " + loggedInUser.getEmail());
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
            accessUsers.addUser(newUser);
            accessUsers.setLoggedInUser(newUser);

            loggedInUser = newUser;

            // Inform home activity that the user has logged in
            Intent result = new Intent();
            result.putExtra(EXTRA_USER, loggedInUser);
            setResult(RESULT_OK, result);

            finish();
        }
    }

    public void buttonLoginOnClick(View view)
    {
        String email = null;
        String password = null;
        boolean valid = true;

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
                accessUsers.setLoggedInUser(user);
                setContentView(R.layout.activity_home);

                loggedInUser = user;

                // Inform home activity that the user has logged in
                Intent result = new Intent();
                result.putExtra(EXTRA_USER, loggedInUser);
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
        String email = null;
        String password = null;
        String confirmPassword = null;

        EditText editFirstName = (EditText)findViewById(R.id.editFirstName);
        if (!TextUtils.isEmpty(editFirstName.getText().toString())) {
            firstName = editFirstName.getText().toString();
        } else {
            editFirstName.setError("First name cannot be empty");
            valid = false;
        }

        EditText editLastName = (EditText) findViewById(R.id.editLastName);
        if (!TextUtils.isEmpty(editLastName.getText().toString()))
        {
            lastName = editLastName.getText().toString();
        }
        else {
            editLastName.setError("Last name cannot be empty");
            valid = false;
        }

        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        if (!TextUtils.isEmpty(editAddress.getText().toString())) {
            address = editAddress.getText().toString();
        } else {
            editAddress.setError("Address cannot be empty");
            valid = false;
        }

        EditText editCity = (EditText) findViewById(R.id.editCity);
        if (!TextUtils.isEmpty(editCity.getText().toString())) {
            city = editCity.getText().toString();
        } else {
            editCity.setError("City cannot be empty");
            valid = false;
        }

        EditText editProvince = (EditText)findViewById(R.id.editProvince);
        if (!TextUtils.isEmpty(editProvince.getText().toString())) {
            province = editProvince.getText().toString();
        } else {
            editProvince.setError("Province cannot be empty");
            valid = false;
        }

        EditText editZipCode = (EditText)findViewById(R.id.editZipCode);
        if (!TextUtils.isEmpty(editZipCode.getText().toString())) {
            if (editZipCode.getText().toString().length() == 6)
            {
                zipCode = editZipCode.getText().toString();
            }
            else {
                editZipCode.setError("Zip Code must be of length 6");
                valid = false;
            }
        } else {
            editZipCode.setError("Zip Code cannot be empty");
            valid = false;
        }

        EditText editPhone = (EditText) findViewById(R.id.editPhone);
        if (!TextUtils.isEmpty(editPhone.getText().toString())) {
            try {
                phone = Integer.parseInt(editPhone.getText().toString());
                if (editPhone.getText().toString().length() != 10)
                {
                    phone = 0;
                    valid = false;
                    editPhone.setError("Phone must contain 10 digits.");
                }
            }
            catch (NumberFormatException e)
            {
                editPhone.setError("Phone can only contain numbers.");
            }
        } else {
            editPhone.setError("Phone cannot be empty");
            valid = false;
        }

        EditText editEmail = (EditText) findViewById(R.id.editEmail);
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

        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        if (!TextUtils.isEmpty(editPassword.getText().toString())) {
            password = editPassword.getText().toString();
            if (password.length() < 6)
            {
                password = null;
                valid = false;
                editPassword.setError("Password must contain at least 6 characters");
            }
        } else {
            editPassword.setError("Password cannot be empty");
            valid = false;
        }

        EditText editConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
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
            newUser = new User(accessUsers.getNumUsers() + 1, firstName, lastName, address, city, province, zipCode, phone, email, password);
        }

        return newUser;
    }

    public void buttonInitializeUpdateUserNameOnClick(View v)
    {
        System.out.println("Inside buttonInitializeUpdateUserNameOnClick");
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
        System.out.println("Inside buttonInitializeUpdateUserPhoneOnClick");
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
        System.out.println("Inside buttonCompleteUpdateUserPhoneOnClick");
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
                    System.out.println("end");

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
        System.out.println("Inside buttonInitializeUpdateUserEmailOnClick");
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
        System.out.println("Inside buttonCompleteUpdateUserEmailOnClick");
        EditText editEmail = (EditText) findViewById(R.id.editViewUserEmail);
        String email;
        if (!TextUtils.isEmpty(editEmail.getText().toString())) {
            System.out.println("inside !TextUtils.isEmpty(editEmail.getText().toString()))");
            email = editEmail.getText().toString();
            if (!email.contains("@") || !email.contains("."))
            {
                editEmail.setError("Not a valid email");
            }
            else {
                loggedInUser.updateEmail(email);
                accessUsers.updateUser(loggedInUser);
                System.out.println("email = " + email);
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

        // Add the "Yes" button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed, delete the item
                accessUsers.deleteUser(loggedInUser);
                Intent intent = new Intent(UsersActivity.this, HomeActivity.class);
                UsersActivity.this.startActivity(intent);
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
        Intent result = new Intent();
        result.putExtra(EXTRA_USER, (String) null);
        setResult(RESULT_OK, result);
        finish();
    }
}
