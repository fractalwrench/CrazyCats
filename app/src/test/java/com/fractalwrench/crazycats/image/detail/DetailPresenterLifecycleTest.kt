package com.fractalwrench.crazycats.image.detail

import com.fractalwrench.crazycats.image.PresenterLifecycleTest
import com.fractalwrench.crazycats.mocks.MockImageDetailView
import com.fractalwrench.crazycats.mocks.TestDependencies

class DetailPresenterLifecycleTest : PresenterLifecycleTest<ImageDetailView, ImageDetailPresenter>() {

    override val contentViewPresenter: ImageDetailPresenter
        get() = ImageDetailPresenter(TestDependencies.mockSuccessRepository())

    override val contentView: ImageDetailView
        get() = MockImageDetailView()

}
