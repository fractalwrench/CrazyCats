package com.fractalwrench.crazycats.image.list;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.mocks.MockImageListCellDelegate;
import com.fractalwrench.crazycats.mocks.MockSuccessRepository;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.fractalwrench.crazycats.RecyclerViewActions.atPosition;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Ensures that the Image List view displays the correct UI when told by its presenter.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageListActivityTest {

    @Rule public ActivityTestRule<ImageListActivity> rule = new ActivityTestRule<>(
            ImageListActivity.class, true, true);
    private ImageListActivity activity;
    private List<ImageData> summaries;

    @Before
    public void setUp() throws Exception {
        activity = rule.getActivity();
        MockSuccessRepository repository = TestDependencies.mockSuccessRepository();

        //noinspection ConstantConditions
        repository.fetchImageSummaries().subscribe(values -> this.summaries = values);
    }

    @Test
    public void testActivityDisplays() throws Throwable {
        onView(withId(R.id.image_list_root)).check(matches(isDisplayed()));
        assertFalse(summaries.isEmpty());
        onView(withId(R.id.image_list_root)).check(matches(isDisplayed()));
    }

    @Test
    public void testProgressDisplays() throws Throwable {
        rule.runOnUiThread(() -> {
            activity.showError(null);
            activity.showProgress();
        });
        onView(withId(R.id.image_list_progress)).check(matches(isDisplayed()));
    }

    @Test
    public void testErrorDisplays() throws Throwable {
        String errorMessage = "test error";

        rule.runOnUiThread(() -> {
            activity.showProgress();
            activity.showError(errorMessage);
        });

        onView(withId(R.id.image_list_err)).check(matches(isDisplayed()));
        onView(withId(R.id.image_list_err)).check(matches(withText(errorMessage)));
    }

    @Test
    public void testContentDisplays() throws Throwable {
        populateContent();
        onView(withId(R.id.image_list_content)).check(matches(isDisplayed()));
    }

    private void populateContent() throws Throwable {
        rule.runOnUiThread(() -> {
            activity.showProgress();
            activity.showContent(summaries);
        });
    }

    /**
     * Checks that an individual cell displays summary information about the post
     */
    @Test
    public void testRecyclerViewCellContent() throws Throwable {
        populateContent();
        int position = 0;
        ImageData image = summaries.get(position);

        String[] displayValues = new String[]{};

        for (String value : displayValues) {
            onView(withId(R.id.image_list_content)).check(
                    matches(atPosition(position, hasDescendant(withText(value)))));
        }
    }

    @Test
    public void testRecyclerViewClick() throws Throwable {
        MockImageListCellDelegate delegate = new MockImageListCellDelegate();
        int position = 0;
        assertNull(delegate.ImageData);

        rule.runOnUiThread(() -> {
            activity.showContent(summaries);
            activity.setDelegate(delegate);
        });

        onView(withId(R.id.image_list_content)).perform(actionOnItemAtPosition(position, click()));
        assertEquals(summaries.get(position), delegate.ImageData);
    }

}
