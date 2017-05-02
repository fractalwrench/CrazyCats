package com.fractalwrench.crazycats.image

import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Ensures that a [Presenter] responds to lifecycle methods as expected.
 *
 *
 * More precisely, there should never be imbalanced calls to [Presenter.start]
 * and [Presenter.stop], and the View should always be dereferenced after
 * presentation ends

 * @param <V> the view type
 * *
 * @param </V><P> the presenter type
</P> */
abstract class PresenterLifecycleTest<V, out P : Presenter<V>> {

    private var presenter: P? = null

    @Before
    @Throws(IOException::class)
    fun setup() {
        presenter = getContentViewPresenter()
        assertNotNull(presenter)
    }

    /**
     * Supply the Presenter to be tested
     */
    protected abstract fun getContentViewPresenter(): P

    @Test
    fun testStartStop() {
        assertNull(presenter!!.contentView)
        presenter!!.start(contentView)
        assertEquals(presenter!!.contentView, presenter!!.contentView)

        presenter!!.stop()
        assertNull(presenter!!.contentView)
    }

    /**
     * Supplies the Content View to be tested.
     *
     *
     * Should be a mocked interface that can run on a JVM without instrumentation.
     */
    protected abstract val contentView: V

    @Test(expected = IllegalStateException::class)
    fun testStopFirst() {
        presenter!!.stop()
    }

    @Test(expected = IllegalStateException::class)
    fun testStartTwice() {
        presenter!!.start(contentView)
        presenter!!.start(contentView)
    }

    @Test(expected = IllegalStateException::class)
    fun testStopTwice() {
        presenter!!.start(contentView)
        presenter!!.stop()
        presenter!!.stop()
    }

}