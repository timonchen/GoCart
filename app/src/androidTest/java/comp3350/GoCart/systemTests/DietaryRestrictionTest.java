package comp3350.GoCart.systemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import comp3350.GoCart.R;
import comp3350.GoCart.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DietaryRestrictionTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void dietaryRestriction (){

        //login to account
        Const.login(Const.User1Name,Const.User1pass);


        //Finds store "Walmart" by name and selects it
        onView(withId(R.id.findStoreButton)).perform(click());
        onView(withId(R.id.strByNameButton)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Walmart"));
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.storesRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //Select peanut free product button and click
        onView(withId(R.id.allergenSwitch)).perform(click());

        // Verify product names of dpeanut free products
        String[] productNames = {"Beef Jerkey", "Ice-cream", "toilet paper", "Banana"}; // Replace with expected product names
        for (int i = 0; i < productNames.length; i++) {
            onView(withId(R.id.productsRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(i, MyViewAction.checkChildTextViewWithId(R.id.productName, productNames[i])));
        }


    }



}
