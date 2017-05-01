package com.fractalwrench.crazycats.image.detail

import com.fractalwrench.crazycats.image.Presenter
import com.fractalwrench.crazycats.image.data.ImageDataRepository


/**
 * Presents a [ImageDetailView] by responding to any UI events, and fetching data if needed.
 */
class ImageDetailPresenter(private val repository: ImageDataRepository) : Presenter<ImageDetailView>() {

    private var id: String? = null

    override fun start(contentView: ImageDetailView) {
        super.start(contentView)
        compositeDisposable?.add(
                repository.fetchImageById(id!!)
                        .subscribe({ contentView.showContent(it) },{ this.displayErrorMessage(it) })
        )
    }

    fun setId(id: String) {
        this.id = id
    }

    private fun displayErrorMessage(throwable: Throwable) {
        // TODO would change message depending on error condition in prod app
        contentView!!.showError("Failed to fetch Image details.")
    }

}
