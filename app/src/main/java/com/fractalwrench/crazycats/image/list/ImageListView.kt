package com.fractalwrench.crazycats.image.list

import com.fractalwrench.crazycats.image.ContentLoadingView
import com.fractalwrench.crazycats.image.data.ImageData

/**
 * Displays a scrollable View of Images
 */
interface ImageListView : ContentLoadingView<List<ImageData>> {

    fun setDelegate(delegate: CellDelegate?)

    fun showImageDetail(imageData: ImageData)

    interface CellDelegate {
        fun onImageCellClicked(imageData: ImageData)
    }

}
