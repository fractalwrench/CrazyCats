package com.fractalwrench.crazycats.image.detail;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.mocks.MockSuccessRepository;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Ensures that the Image Detail view displays the correct UI when told by its presenter.
 * <p>
 * The test consists of checking that either the Content, Progress, or Error View is displayed at
 * the correct time. For the Content, it is also checked that TextViews display the correct info.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageDetailActivityTest {

    @Rule public ActivityTestRule<ImageDetailActivity> rule = new ActivityTestRule<>(
            ImageDetailActivity.class, true, true);

    private ImageDetailActivity activity;
    private ImageDetail imageDetail;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        MockSuccessRepository repository = TestDependencies.mockSuccessRepository();

        repository.fetchImageById("").subscribe(ImageDetail -> {
            this.imageDetail = ImageDetail;
        });
    }

    @Test
    public void testActivityDisplays() throws Throwable {
        onView(withId(R.id.image_detail_root)).check(matches(isDisplayed()));
        onView(withId(R.id.image_detail_content)).check(matches(isDisplayed()));
    }

    @Test
    public void testProgressDisplays() throws Throwable {
        rule.runOnUiThread(() -> {
            activity.showError(null);
            activity.showProgress();
        });
        onView(withId(R.id.image_detail_progress)).check(matches(isDisplayed()));
    }

    @Test
    public void testErrorDisplays() throws Throwable {
        String msg = "Test failure, please ignore";
        rule.runOnUiThread(() -> {
            activity.showProgress();
            activity.showError(msg);
        });
        onView(withId(R.id.image_detail_err)).check(matches(isDisplayed()));
        onView(withId(R.id.image_detail_err)).check(matches(withText(msg)));
    }

    @Test
    public void testContentDisplays() throws Throwable {
        rule.runOnUiThread(() -> {
            activity.showProgress();
            activity.showContent(imageDetail);
        });
        onView(withId(R.id.image_detail_content)).check(matches(isDisplayed()));

        // check data binding
        Map<Integer, String> map = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            onView(withId(entry.getKey())).check(matches(withText(entry.getValue())));
        }
    }

}