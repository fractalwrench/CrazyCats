package com.fractalwrench.crazycats.image.list;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.list.ImageListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Ensures that the Image List Activity launches
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageListSmokeTest {

    @Rule public ActivityTestRule<ImageListActivity> rule = new ActivityTestRule<>(
            ImageListActivity.class, true, true);
    private ImageListActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
    }

    @Test
    public void testActivityDisplays() throws Throwable {
        onView(ViewMatchers.withId(R.id.image_list_root)).check(matches(isDisplayed()));
    }

}
