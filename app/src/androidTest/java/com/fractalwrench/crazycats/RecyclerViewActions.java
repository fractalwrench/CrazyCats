package com.fractalwrench.crazycats;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.fractalwrench.crazycats.common.PreConditions.checkNonNull;

/**
 * From http://stackoverflow.com/a/34795431/5144991
 */
public class RecyclerViewActions {

    public static Matcher<View> atPosition(final int position,
                                           @NonNull final Matcher<View> itemMatcher) {
        checkNonNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(
                        position);
                // has no item on such position
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }


}
