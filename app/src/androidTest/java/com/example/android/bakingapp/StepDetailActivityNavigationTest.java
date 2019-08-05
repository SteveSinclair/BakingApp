package com.example.android.bakingapp;


import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.activities.StepDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class StepDetailActivityNavigationTest {

    @Rule
    public ActivityTestRule<StepDetailActivity> activityRule = new ActivityTestRule<>(StepDetailActivity.class);

// Find the view
// Perform action on the view
// Check if the view does what you expected

    @Test
    public void clickNextButton_ShowsNextStep() {

        onView(withId(R.id.buttonNextStep)).perform(click());
        onView(withId(R.id.buttonNextStep)).check(matches(isEnabled()));
    }

}
