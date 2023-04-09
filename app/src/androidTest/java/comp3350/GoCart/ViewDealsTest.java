package comp3350.GoCart;


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
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import comp3350.GoCart.presentation.HomeActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewDealsTest {
    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void findDeal(){
        //login to account
        onView(withId(R.id.loginButtonOnStart)).perform(click());
        onView(withId(R.id.editLoginEmail)).perform(typeText(Const.User1Name));
        onView(withId(R.id.editLoginPassword)).perform(typeText(Const.User2pass));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Enjoy shopping with GoCart!")).perform(ViewActions.pressBack());

        onView(withId(R.id.discoverDealsButton)).perform(click());

        //first item matches expected item
        onView(withId(R.id.avgPrice)).check(matches(withText("$10.16")));
        onView(withId(R.id.salePrice)).check(matches(withText("$9.90")));

        //check functionality of next button
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.avgPrice)).check(matches(withText("$4.16")));
        onView(withId(R.id.salePrice)).check(matches(withText("$3.90")));

        //functionality of prev button
        onView(withId(R.id.prevButton)).perform(click());
        onView(withId(R.id.avgPrice)).check(matches(withText("$13.16")));
        onView(withId(R.id.salePrice)).check(matches(withText("$12.90")));


    }

}
