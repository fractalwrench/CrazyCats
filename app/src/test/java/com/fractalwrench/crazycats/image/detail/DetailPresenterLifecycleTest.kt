package com.fractalwrench.crazycats.image.detail

import com.fractalwrench.crazycats.image.PresenterLifecycleTest
import com.fractalwrench.crazycats.mocks.MockImageDetailView
import com.fractalwrench.crazycats.mocks.TestDependencies

import java.io.IOException

class DetailPresenterLifecycleTest : PresenterLifecycleTest<ImageDetailView, ImageDetailPresenter>() {

    @Throws(IOException::class)
    override fun getContentViewPresenter(): ImageDetailPresenter {
        return ImageDetailPresenter(TestDependencies.mockSuccessRepository())
    }

    override fun getContentView(): ImageDetailView {
        return MockImageDetailView()
    }
}
