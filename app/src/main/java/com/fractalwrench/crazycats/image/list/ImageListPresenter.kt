package com.fractalwrench.crazycats.image.list

import com.fractalwrench.crazycats.image.Presenter
import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.data.ImageDataRepository


/**
 * Presents a [ImageListView], and responds to UI events by fetching data if needed
 */
class ImageListPresenter(private val repository: ImageDataRepository) : Presenter<ImageListView>(), ImageListView.CellDelegate {

    override fun start(contentView: ImageListView) {
        super.start(contentView)
        contentView.setDelegate(this)
        fetchImageSuggestions()
    }

    override fun stop() {
        if (contentView != null) {
            contentView!!.setDelegate(null)
        }
        super.stop()
    }

    /** ImageListView.CellDelegate  */

    override fun onImageCellClicked(imageData: ImageData) {
        contentView!!.showImageDetail(imageData)
    }

    fun fetchImageSuggestions() {
        contentView!!.showProgress()
        compositeDisposable?.add(repository.fetchImageSummaries()
                .subscribe({ contentView!!.showContent(it) },
                        { this.handleImageFetchFailure(it) }))
    }

    /** Private impl  */

    private fun handleImageFetchFailure(throwable: Throwable) {
        // TODO would change message (and localise) depending on error condition in prod app
        contentView!!.showError("Failed to fetch your cats images! Tap here to retry.")
    }

}

