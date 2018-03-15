package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Nirav on 2018-03-15.
 */

public class createTest {

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    /**
     *
     * @throws Exception: create operation test
     * here it will click on create contact button and it will redirect to another activity and ask them for information such as
     * name, email, number, address, business type and province. and once they click to submit
     * it will store the data to firebase and also it will populate the list items
     */
    @Test
    public void a_createTest() throws Exception {
        String name = "Nirav Jadeja";
        String email = "nirav9033@gmail.com";
        String number = "123123123";
        String address = "Halifax, Canada";

        // clicking the create button
        onView(withId(R.id.submitButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));
        onView(withId((R.id.name))).perform(click()).perform(typeText(name), closeSoftKeyboard());
        onView(withId((R.id.email))).perform(click()).perform(typeText(email), closeSoftKeyboard());
        onView(withId((R.id.userNumber))).perform(click()).perform(typeText(number),closeSoftKeyboard());
        onView(withId((R.id.userAddress))).perform(click()).perform(typeText(address), closeSoftKeyboard());

        onView(withId(R.id.spinner1)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());

        onView(withId(R.id.spinner2)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(2).perform(click());

        Thread.sleep(1500);
        onView(withId(R.id.submitButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));

    }

    /**
     *
     * @throws Exception update operation tests
     * In this test, it will select a item from list items and this will redirect to another activity
     * in this activity this test will change the user's contact number and will update it again by clicking the update button
     * at last it will show the list items
     */
    @Test
    public void b_updateTest() throws Exception {

        String replaceText = "999888777";
        Thread.sleep(1000); // 1 seconds delay

        onView(withId((R.id.listView))).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click()); //opening a list item

        onView(withId((R.id.userNumber))).perform(click()).perform(replaceText(replaceText), closeSoftKeyboard()); // replacing data

        Thread.sleep(1000); // 1 seconds delay

        onView(withId(R.id.updateButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));
    }

    /**
     * @throws Exception Delete Test
     * here is the delete test which opens a list item and delete the item once it clicks on erase button
     */
    @Test
    public void c_deleteTest() throws Exception {

        Thread.sleep(1000); // 1 seconds delay

        onView(withId((R.id.listView))).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click()); //opening a list item

        Thread.sleep(1000);

        onView(withId(R.id.deleteButton)).perform(new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER));
    }


}


