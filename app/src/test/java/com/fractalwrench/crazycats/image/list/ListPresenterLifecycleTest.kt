package com.fractalwrench.crazycats.image.list

import com.fractalwrench.crazycats.image.PresenterLifecycleTest
import com.fractalwrench.crazycats.mocks.MockImageListView
import com.fractalwrench.crazycats.mocks.TestDependencies

class ListPresenterLifecycleTest : PresenterLifecycleTest<ImageListView, ImageListPresenter>() {

    override fun getContentViewPresenter(): ImageListPresenter = ImageListPresenter(TestDependencies.mockSuccessRepository())

    override val contentView: ImageListView
        get() = MockImageListView()

}
