package comp3350.GoCart.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessUsers;
import comp3350.GoCart.objects.User;

public class UsersActivity extends Activity
{
    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("oncreate");
        super.onCreate(savedInstanceState);
        accessUsers = new AccessUsers();

        if (accessUsers.getLoggedInUser() == null)
        {
            setContentView(R.layout.activity_login);
        }
        else
        {
            setContentView(R.layout.activity_user_account);
        }
        Toast.makeText(this, "ORAYO2", Toast.LENGTH_SHORT).show();
    }

    public void buttonSignUpPageOnClick(View v)
    {
        setContentView(R.layout.activity_signup);
    }

    public void buttonUserSignUpOnClick(View v)
    {
        User newUser = createUserFromEditText();

        if (newUser == null)    // Change to !=
        {
            accessUsers.addUser(newUser);

            accessUsers.setLoggedInUser(newUser);

            setContentView(R.layout.activity_home);
            Button loginButton = (Button)findViewById(R.id.loginButton);
            loginButton.setVisibility(View.GONE);

            Button userAccountButton = (Button)findViewById(R.id.userAccountButton);
            userAccountButton.setVisibility(View.VISIBLE);

            String accountCreated = "Account Created";
            String welcomeMessage = "Enjoy shopping with GoCart!";
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle(accountCreated);
            alertDialog.setMessage(welcomeMessage);

            alertDialog.show();
        }

        /*
        List<User> allUsers = accessUsers.getUsers();

        System.out.println("All Users");

        for (int i = 0; i < allUsers.size(); i++)
        {
            System.out.println(allUsers.get(i));
        } */
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

                Button loginButton = (Button)findViewById(R.id.loginButton);
                loginButton.setVisibility(View.GONE);

                Button userAccountButton = (Button)findViewById(R.id.userAccountButton);
                userAccountButton.setVisibility(View.VISIBLE);

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }

    private User createUserFromEditText()
    {
        User newUser = null;

        /*
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
            newUser = new User(firstName, lastName, address, city, province, zipCode, phone, email, password);
        } */

        return newUser;
    }

    public void buttonInitializeUpdateUserNameOnClick()
    {
        TextView textView = (TextView) findViewById(R.id.textViewUserName);
        EditText editText = (EditText) findViewById(R.id.editTextUserName);
        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
    }
}
