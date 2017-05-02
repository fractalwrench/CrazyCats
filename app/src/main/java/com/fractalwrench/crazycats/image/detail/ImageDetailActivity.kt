package com.fractalwrench.crazycats.image.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fractalwrench.crazycats.BaseActivity
import com.fractalwrench.crazycats.R
import com.fractalwrench.crazycats.image.data.ImageData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_detail.*
import javax.inject.Inject

/**
 * Displays details about a single image, allowing the user to interact with it.
 */
class ImageDetailActivity : BaseActivity(), ImageDetailView {

    override val layoutResId = R.layout.activity_image_detail

    private var imageDetailComponent: ImageDetailComponent? = null
    @Inject lateinit var presenter: ImageDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageDetailComponent = appComponent.plus(ImageDetailModule())
        imageDetailComponent!!.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        imageDetailComponent = null // release reference
    }

    override fun onStart() {
        super.onStart()
        val summaryId = intent.getStringExtra(KEY_IMAGE_ID)
        presenter.setId(summaryId)
        presenter.start(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
        Picasso.with(this)
                .cancelRequest(image_detail_photo_view)
    }


    /** ImageDetailView implementation  */


    override fun showProgress() {
        image_detail_root!!.displayedChild = VIEW_PROGRESS
    }

    override fun showContent(content: ImageData) {
        image_detail_root!!.displayedChild = VIEW_CONTENT
        image_detail_backstory!!.text = content.title

        val context = this

        // load thumbnail URL first as should already be in cache, then full image
        Picasso.with(context)
                .load(content.thumbnailUrl)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(image_detail_photo_view!!, object : Callback {
                    override fun onSuccess() {
                        Picasso.with(context)
                                .load(content.imageUrl)
                                .placeholder(image_detail_photo_view!!.drawable) // use thumbnail as placeholder
                                .into(image_detail_photo_view)
                    }

                    override fun onError() {
                        // no-op
                    }
                })
    }

    override fun showError(errorMessage: String) {
        image_detail_root!!.displayedChild = VIEW_ERR
        image_detail_err!!.text = errorMessage
    }

    companion object {

        private val KEY_IMAGE_ID = "KEY_IMAGE_ID"

        fun getIntent(context: Context, imageData: ImageData): Intent {
            val intent = Intent(context, ImageDetailActivity::class.java)
            intent.putExtra(KEY_IMAGE_ID, imageData.id)
            return intent
        }
    }

}
