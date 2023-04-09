package comp3350.GoCart;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;

public class Const {
    private Const(){}
    public static final String User1Name = "testuser@gmail.com";
    public static final String User1pass = "testuser";
    public static final String User2Name = "testuser@gmail.com";
    public static final String User2pass = "testuser";

    public static final void login(){
        onView(withId(R.id.loginButtonOnStart)).perform(click());
        onView(withId(R.id.editLoginEmail)).perform(typeText(Const.User1Name));
        onView(withId(R.id.editLoginPassword)).perform(typeText(Const.User2pass));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Enjoy shopping with GoCart!")).perform(ViewActions.pressBack());
    }
}
