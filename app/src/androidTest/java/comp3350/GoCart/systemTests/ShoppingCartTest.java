package comp3350.GoCart.systemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import comp3350.GoCart.R;
import comp3350.GoCart.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShoppingCartTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void AddToCart(){

        //login to account
        Const.login(Const.User1Name,Const.User1pass);


        //Finds store "Walmart" by name and selects it
        onView(withId(R.id.findStoreButton)).perform(click());
        onView(withId(R.id.strByNameButton)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Walmart"));
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.storesRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //Add items to cart
        onView(withId(R.id.productsRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,MyViewAction.clickChildViewWithId(R.id.btnAddToCart)));
        onView(withId(R.id.productsRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(1,MyViewAction.clickChildViewWithId(R.id.btnAddToCart)));

        //Enters cart activty
        onView(withId(R.id.cart_fab)).perform(click());

        //verify items added correctly with right quantities
        onView(withId(R.id.txtEditTotalPrice)).check(matches(withText("22.98")));

        //Change quantities of items in cart
        onView(withId(R.id.cartRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,MyViewAction.clickChildViewWithId(R.id.btnIncQuantity)));
        onView(withId(R.id.cartRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(1,MyViewAction.clickChildViewWithId(R.id.btnDecQuantity)));

        //Verify quantity buttons change quantities as expected
        onView(withId(R.id.txtEditTotalPrice)).check(matches(withText("19.98")));

        onView(withId(R.id.cartRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(1,MyViewAction.enterValueIntoTextView(R.id.txtSetQuantity,"5")));
        onView(withId(R.id.cartRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(1,MyViewAction.clickChildViewWithId(R.id.btnChangeQuantity)));

        onView(withId(R.id.txtEditTotalPrice)).check(matches(withText("84.93")));


    }



}
