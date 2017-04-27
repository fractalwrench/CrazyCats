package com.fractalwrench.crazycats.image;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Ensures that a {@link Presenter} responds to lifecycle methods as expected.
 * <p>
 * More precisely, there should never be imbalanced calls to {@link Presenter#start(V)}
 * and {@link Presenter#stop()}, and the View should always be dereferenced after
 * presentation ends
 *
 * @param <V> the view type
 * @param <P> the presenter type
 */
public abstract class PresenterLifecycleTest<V, P extends Presenter<V>> {

    private P presenter;

    @Before
    public void setup() throws IOException {
        presenter = getContentViewPresenter();
        assertNotNull(presenter);
    }

    /**
     * Supply the Presenter to be tested
     */
    @NonNull
    protected abstract P getContentViewPresenter() throws IOException;

    @Test(expected = IllegalArgumentException.class)
    public void testNullView() {
        //noinspection ConstantConditions
        presenter.start(null);
    }

    @Test
    public void testStartStop() {
        assertNull(presenter.contentView);
        presenter.start(getContentView());
        assertEquals(presenter.contentView, presenter.contentView);

        presenter.stop();
        assertNull(presenter.contentView);
    }

    /**
     * Supplies the Content View to be tested.
     * <p>
     * Should be a mocked interface that can run on a JVM without instrumentation.
     */
    @NonNull
    protected abstract V getContentView();

    @Test(expected = IllegalStateException.class)
    public void testStopFirst() {
        presenter.stop();
    }

    @Test(expected = IllegalStateException.class)
    public void testStartTwice() {
        presenter.start(getContentView());
        presenter.start(getContentView());
    }

    @Test(expected = IllegalStateException.class)
    public void testStopTwice() {
        presenter.start(getContentView());
        presenter.stop();
        presenter.stop();
    }

}