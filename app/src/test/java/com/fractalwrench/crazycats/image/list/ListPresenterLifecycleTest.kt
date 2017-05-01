package com.fractalwrench.crazycats.image.list

import com.fractalwrench.crazycats.image.PresenterLifecycleTest
import com.fractalwrench.crazycats.mocks.MockImageListView
import com.fractalwrench.crazycats.mocks.TestDependencies

import java.io.IOException

class ListPresenterLifecycleTest : PresenterLifecycleTest<ImageListView, ImageListPresenter>() {

    @Throws(IOException::class)
    override fun getContentViewPresenter(): ImageListPresenter {
        return ImageListPresenter(TestDependencies.mockSuccessRepository())
    }

    override fun getContentView(): ImageListView {
        return MockImageListView()
    }

}
