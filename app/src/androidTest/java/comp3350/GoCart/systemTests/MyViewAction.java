package comp3350.GoCart.systemTests;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class MyViewAction {

    // Click on a child view with a specified id
    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    // Enter a value into an EditText child view with a specified id
    public static ViewAction enterValueIntoTextView(final int id, final String value) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                EditText v = view.findViewById(id);
                v.setText(value);
            }
        };
    }

    // Check the text of a TextView child view with a specified id and compare it with the expected text
    public static ViewAction checkChildTextViewWithId(final int id, final String expectedText) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Check a child TextView with the specified id and compare its text.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView v = view.findViewById(id);
                if (!expectedText.equals(v.getText().toString())) {
                    throw new AssertionError("Expected text: " + expectedText + ", but was: " + v.getText());
                }
            }
        };
    }
}
