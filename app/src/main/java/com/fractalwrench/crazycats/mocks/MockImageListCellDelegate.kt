package com.fractalwrench.crazycats.mocks


import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.list.ImageListView

class MockImageListCellDelegate : ImageListView.CellDelegate {

    var imageData: ImageData? = null

    override fun onImageCellClicked(imageData: ImageData) {
        this.imageData = imageData
    }

}
