package com.fractalwrench.crazycats.image.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.OnClick
import com.fractalwrench.crazycats.BaseActivity
import com.fractalwrench.crazycats.R
import com.fractalwrench.crazycats.image.data.ImageData
import com.fractalwrench.crazycats.image.detail.ImageDetailActivity
import kotlinx.android.synthetic.main.activity_image_list.*
import javax.inject.Inject

/**
 * Displays a searchable list of Images, and allows the user to select an item to view in more
 * detail, which will launch the [ImageDetailActivity]
 */
class ImageListActivity : BaseActivity(), ImageListView {

    override val layoutResId = R.layout.activity_image_list

    private var imageListComponent: ImageListComponent? = null
    @Inject lateinit var presenter: ImageListPresenter
    @Inject lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject lateinit var imageListAdapter: ImageListAdapter

    private var delegate: ImageListView.CellDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageListComponent = appComponent.plus(ImageListModule(this))
        imageListComponent!!.inject(this)

        image_list_content!!.layoutManager = layoutManager
        image_list_content!!.adapter = imageListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        imageListComponent = null // release reference
    }

    override fun onStart() {
        super.onStart()
        presenter!!.start(this)
        imageListAdapter!!.setDelegate(delegate)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.stop()
        imageListAdapter!!.setDelegate(null)
    }


    /** ImageListView  */


    @OnClick(R.id.image_list_err)
    internal fun onErrorClicked() {
        presenter!!.fetchImageSuggestions()
    }

    override fun setDelegate(delegate: ImageListView.CellDelegate?) {
        this.delegate = delegate
        imageListAdapter!!.setDelegate(delegate)
    }

    override fun showProgress() {
        image_list_root!!.displayedChild = VIEW_PROGRESS
    }

    override fun showContent(content: List<ImageData>) {
        image_list_root!!.displayedChild = VIEW_CONTENT
        imageListAdapter!!.updateImages(content)
        imageListAdapter!!.notifyDataSetChanged()
    }

    override fun showError(errorMessage: String) {
        image_list_root!!.displayedChild = VIEW_ERR
        image_list_err!!.text = errorMessage
    }

    override fun showImageDetail(imageData: ImageData) {
        startActivity(ImageDetailActivity.getIntent(this, imageData))
    }
}
