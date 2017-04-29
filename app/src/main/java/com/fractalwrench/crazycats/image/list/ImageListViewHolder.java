package com.fractalwrench.crazycats.image.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.fractalwrench.crazycats.R;

import butterknife.ButterKnife;

class ImageListViewHolder extends RecyclerView.ViewHolder {

    final ImageView imageView;

    ImageListViewHolder(View itemView) {
        super(itemView);
        imageView = ButterKnife.findById(itemView, R.id.image_summary_view);
    }

}
