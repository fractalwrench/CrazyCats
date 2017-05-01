package com.fractalwrench.crazycats.image.detail


import com.fractalwrench.crazycats.mocks.MockImageDetailView
import com.fractalwrench.crazycats.mocks.TestDependencies

import org.junit.After
import org.junit.Before
import org.junit.Test

import java.io.IOException

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

class DetailPresenterSuccessTest {

    private var presenter: ImageDetailPresenter? = null
    private var detailView: MockImageDetailView? = null

    @Before
    @Throws(IOException::class)
    fun setup() {
        presenter = ImageDetailPresenter(TestDependencies.mockSuccessRepository())
        detailView = MockImageDetailView()
    }

    @After
    fun tearDown() {
        presenter!!.stop()
    }

    /**
     * Ensures that the view is notified when the presenter fetches data
     */
    @Test
    fun checkPostListFailure() {
        assertNull(detailView!!.imageData)
        assertNull(detailView!!.errorMessage)

        presenter!!.start(detailView!!)
        assertNotNull(detailView!!.imageData)
        assertNull(detailView!!.errorMessage)
    }

}
