package comp3350.GoCart.systemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import comp3350.GoCart.R;

public class Const {
    private Const(){}
    public static final String User1Name = "testuser1@gmail.com";
    public static final String User1pass = "testuser1";
    public static final String User2Name = "testuser2@gmail.com";
    public static final String User2pass = "testuser2";

    public static final void login(String uName,String pass){
        onView(ViewMatchers.withId(R.id.loginButtonOnStart)).perform(click());
        onView(withId(R.id.editLoginEmail)).perform(typeText(uName));
        onView(withId(R.id.editLoginPassword)).perform(typeText(pass));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Enjoy shopping with GoCart!")).perform(ViewActions.pressBack());
    }
}
