package com.example.cardiacrecorder;

import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;

import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MyActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void activityChecker() {
        onView(withId(R.id.add_btn)).perform(click());
        onView(withId(R.id.name_field)).perform(typeText("Nafis"));
        onView(withId(R.id.systolic_field)).perform(typeText("120"));
        onView(withId(R.id.diastolic_field)).perform(typeText("80"));
        onView(withId(R.id.heart_rate_field)).perform(typeText("80"));
        onView(withId(R.id.comments_field)).perform(typeText("Sitting"));
        Espresso.pressBack();
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.next)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.main)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeRight()));
        onView(withId(R.id.name_field)).perform(clearText(),typeText("Leo"));
        onView(withId(R.id.systolic_field)).perform(clearText(),typeText("140"));
        onView(withId(R.id.diastolic_field)).perform(clearText(),typeText("70"));
        onView(withId(R.id.heart_rate_field)).perform(clearText(),typeText("60"));
        onView(withId(R.id.comments_field)).perform(clearText(),typeText("Resting"));
        Espresso.pressBack();
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, swipeLeft()));
    }

}
