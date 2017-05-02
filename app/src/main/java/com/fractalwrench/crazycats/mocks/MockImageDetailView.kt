package com.fractalwrench.crazycats.mocks

import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.detail.ImageDetailView


class MockImageDetailView : ImageDetailView {

    var errorMessage: String? = null
    var imageData: ImageData? = null

    override fun showProgress() {

    }

    override fun showContent(content: ImageData) {
        this.imageData = content
    }

    override fun showError(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}
