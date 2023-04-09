package comp3350.GoCart;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.os.SystemClock;

import comp3350.GoCart.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchProductByCategoryTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void dietaryRestriction (){

        //login to account
        onView(withId(R.id.loginButtonOnStart)).perform(click());
        onView(withId(R.id.editLoginEmail)).perform(typeText("testuser@gmail.com"));
        onView(withId(R.id.editLoginPassword)).perform(typeText("testuser"));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Enjoy shopping with GoCart!")).perform(ViewActions.pressBack());


        //Finds store "Walmart" by name and selects it
        onView(withId(R.id.findStoreButton)).perform(click());
        onView(withId(R.id.strByNameButton)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Walmart"));
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.storesRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //Select peanut free product button and click
        onView(withId(R.id.categorySpinner)).perform(click());

        // Select the sub-category "meat" from the spinner
        onData(allOf(is(instanceOf(String.class)), hasToString("meat")))
                .inRoot(isPlatformPopup())
                .perform(click());

        //verifying that the product displayed is meat
        onView(withId(R.id.productsRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.checkChildTextViewWithId(R.id.productName, "Beef Jerkey")));




    }



}
