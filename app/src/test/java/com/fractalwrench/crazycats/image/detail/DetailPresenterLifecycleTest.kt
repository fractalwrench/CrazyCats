package com.fractalwrench.crazycats.image.detail

import com.fractalwrench.crazycats.image.PresenterLifecycleTest
import com.fractalwrench.crazycats.mocks.MockImageDetailView
import com.fractalwrench.crazycats.mocks.TestDependencies

class DetailPresenterLifecycleTest : PresenterLifecycleTest<ImageDetailView, ImageDetailPresenter>() {

    override fun getContentViewPresenter(): ImageDetailPresenter {
        val presenter = ImageDetailPresenter(TestDependencies.mockSuccessRepository())
        presenter.setId("")
        return presenter
    }

    override val contentView: ImageDetailView
        get() = MockImageDetailView()

}
