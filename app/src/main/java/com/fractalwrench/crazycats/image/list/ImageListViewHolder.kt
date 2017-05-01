package com.fractalwrench.crazycats.image.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import com.fractalwrench.crazycats.R

internal class ImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = ButterKnife.findById<ImageView>(itemView, R.id.image_summary_view)
    val titleView: TextView = ButterKnife.findById<TextView>(itemView, R.id.image_summary_cell_title)

}
