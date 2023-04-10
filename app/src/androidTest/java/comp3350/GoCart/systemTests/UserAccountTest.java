package comp3350.GoCart.systemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.action.ViewActions;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import java.io.File;

import comp3350.GoCart.R;
import comp3350.GoCart.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserAccountTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);
    private File tempDB;


    @Test
    public void login() {

        //login to account
        Const.login(Const.User1Name,Const.User1pass);
        //Verify user is logged in
        onView(ViewMatchers.withId(R.id.userAccountButton)).check(matches(withText("TU")));
    }
    @Test
    public void logout() {
        //login to account
        Const.login(Const.User1Name,Const.User1pass);
        //Verify user is logged in
        onView(withId(R.id.userAccountButton)).check(matches(withText("TU")));
        onView(withId(R.id.userAccountButton)).perform(click());
        onView(withId(R.id.buttonLogout)).perform(click());

        //verify logout
        onView(withText("Thank you for shopping with GoCart!")).check(matches(isDisplayed()));
    }

    @Test
    public void viewAccount() {
        //login to account
        Const.login(Const.User1Name,Const.User1pass);

        //Verify user is logged in
        onView(withId(R.id.userAccountButton)).check(matches(withText("TU")));
        onView(withId(R.id.userAccountButton)).perform(click());

        onView(withId(R.id.textViewUserName)).check( matches(withText("test1 user1")));
        onView(withId(R.id.textViewUserPhone)).check( matches(withText("User Phone")));
        onView(withId(R.id.textViewUserEmail)).check( matches(withText("testuser1@gmail.com")));
    }



    @Test
    public void CreateanddeleteAccount(){

        onView(withId(R.id.loginButtonOnStart)).perform(click());

        //create new account

        onView(withId(R.id.buttonSignUpPage)).perform(click());
        onView(withId(R.id.editFirstName)).perform(typeText("test"));
        onView(withId(R.id.editLastName)).perform(typeText("account"));
        onView(withId(R.id.editEmail)).perform(typeText("accessability@test.com"));
        onView(withId(R.id.editPassword)).perform(typeText("password"));
        onView(withId(R.id.editConfirmPassword)).perform(typeText("password"));
        onView(withId(R.id.editAddress)).perform(typeText("addr"));
        onView(withId(R.id.editCity)).perform(typeText("city"));
        onView(withId(R.id.editProvince)).perform(typeText("prov"));
        onView(withId(R.id.editZipCode)).perform(typeText("1a1a1a"));
        onView(withId(R.id.editPhone)).perform(typeText("1234567890"));
        onView(withId(R.id.buttonSignUp)).perform(click());

        onView(withText("Enjoy shopping with GoCart!")).perform(ViewActions.pressBack());
        //verify logged in with account
        onView(withId(R.id.userAccountButton)).check(matches(withText("TA")));


        onView(withId(R.id.userAccountButton)).perform(click());
        onView(withId(R.id.buttonDeleteUserAccount)).perform(click());

        onView(withText("YES")).perform(click());


        onView(withId(R.id.loginButtonOnStart)).perform(click());
        onView(withId(R.id.editLoginEmail)).perform(typeText("accessability@test.com"));
        onView(withId(R.id.editLoginPassword)).perform(typeText("password"));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Invalid email/password")).check(matches(isDisplayed()));

    }




}