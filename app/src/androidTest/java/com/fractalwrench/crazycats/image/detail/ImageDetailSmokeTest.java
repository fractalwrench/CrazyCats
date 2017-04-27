package com.fractalwrench.crazycats.image.detail;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.detail.ImageDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Ensures that the Image Detail view launches.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageDetailSmokeTest {

    @Rule public ActivityTestRule<ImageDetailActivity> rule = new ActivityTestRule<>(
            ImageDetailActivity.class, true, true);

    private ImageDetailActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
    }

    @Test
    public void testActivityDisplays() throws Throwable {
        onView(ViewMatchers.withId(R.id.image_detail_root)).check(matches(isDisplayed()));
    }

}
