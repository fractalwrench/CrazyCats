package com.fractalwrench.crazycats.image.list


import com.fractalwrench.crazycats.mocks.MockImageListView
import com.fractalwrench.crazycats.mocks.TestDependencies

import org.junit.After
import org.junit.Before
import org.junit.Test

import java.io.IOException

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

class ListPresenterSuccessTest {

    private var presenter: ImageListPresenter? = null
    private var listView: MockImageListView? = null

    @Before
    @Throws(IOException::class)
    fun setup() {
        presenter = ImageListPresenter(TestDependencies.mockSuccessRepository())
        listView = MockImageListView()
    }

    @After
    fun tearDown() {
        presenter!!.stop()
    }

    /**
     * Ensures that the view is notified when the presenter fetches data
     */
    @Test
    fun checkPostListSuccess() {
        assertNull(listView!!.content)
        assertNull(listView!!.errorMessage)

        presenter!!.start(listView!!)
        assertNotNull(listView!!.content)
        assertNull(listView!!.errorMessage)
    }

}
