package com.fractalwrench.crazycats.image.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.fractalwrench.crazycats.R
import com.fractalwrench.crazycats.image.data.ImageData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.*

class ImageListAdapter internal constructor() : RecyclerView.Adapter<ImageListViewHolder>(), View.OnClickListener {

    private val items: MutableList<ImageData>
    private var inflater: LayoutInflater? = null
    private var delegate: ImageListView.CellDelegate? = null

    init {
        this.items = ArrayList<ImageData>()
    }

    internal fun updateImages(content: List<ImageData>) {
        items.clear()
        items.addAll(content)
        notifyDataSetChanged()
    }

    internal fun setDelegate(delegate: ImageListView.CellDelegate?) {
        this.delegate = delegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        getInflater(parent)
        val view = inflater!!.inflate(R.layout.cell_image_list, parent, false)
        view.setOnClickListener(this)
        return ImageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val (_, _, thumbnailUrl, title) = items[position]
        val context = holder.itemView.context

        holder.itemView.tag = position
        holder.titleView.text = title

        Picasso.with(context)
                .cancelRequest(holder.imageView)

        holder.imageView.scaleType = ImageView.ScaleType.CENTER

        Picasso.with(context)
                .load(thumbnailUrl)
                .placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imageView, object : Callback {
                    override fun onSuccess() {
                        holder.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    }

                    override fun onError() {

                    }
                })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getInflater(parent: ViewGroup) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
    }

    override fun onClick(view: View) {
        if (view.tag is Int) {
            val position = view.tag as Int

            if (position < items.size) {
                val imageData = items[position]

                if (delegate != null) {
                    delegate!!.onImageCellClicked(imageData)
                    return
                }
            }
        }
        throw IllegalStateException(
                "RecyclerView clicked cell without tagged position or Model")
    }

}
