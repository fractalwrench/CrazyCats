package com.fractalwrench.crazycats.mocks

import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.list.ImageListView

class MockImageListView : ImageListView {

    var content: List<ImageData>? = null
    var errorMessage: String? = null

    override fun showProgress() {

    }

    override fun showContent(content: List<ImageData>) {
        this.content = content
    }

    override fun showError(errorMessage: String) {
        this.errorMessage = errorMessage
    }

    override fun setDelegate(delegate: ImageListView.CellDelegate?) {

    }

    override fun showImageDetail(imageData: ImageData) {

    }
}
