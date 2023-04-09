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
public class FindClosestStore {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void findClosestStore() {
        //login to account
        Const.login(Const.User1Name,Const.User1pass);

        //find the closest store
        onView(withId(R.id.findStoreButton)).perform(click());
        onView(withId(R.id.closestStore)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("66 Chancellors Circle Winnipeg"));
        onView(withId(R.id.searchButton)).perform(click());

        //check to make sure the closest store is the costco Mcgillvary as this is the cloest
        onView(withId(R.id.storesRecView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.storeName)).check(matches(withText("Costco")));
        onView(withId(R.id.storeAddress)).check(matches(withText("2365 McGillivray Blvd Winnipeg")));


    }
}
