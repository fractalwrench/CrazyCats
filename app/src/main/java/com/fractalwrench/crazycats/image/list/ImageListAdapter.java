package com.fractalwrench.crazycats.image.list;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fractalwrench.crazycats.R;
import com.fractalwrench.crazycats.image.data.ImageSummary;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> implements
                                                                                View.OnClickListener {

    private final List<ImageSummary> items;
    private LayoutInflater inflater;
    private ImageListView.CellDelegate delegate;

    public ImageListAdapter() {
        this.items = new ArrayList<>();
    }

    void updateImages(List<ImageSummary> content) {
        items.clear();
        items.addAll(content);
        notifyDataSetChanged();
    }

    void setDelegate(@Nullable ImageListView.CellDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        getInflater(parent);
        View view = inflater.inflate(R.layout.cell_image_list, parent, false);
        view.setOnClickListener(this);
        return new ImageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, int position) {
        ImageSummary imageSummary = items.get(position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void getInflater(ViewGroup parent) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof Integer) {
            int position = (int) view.getTag();

            if (position < items.size()) {
                ImageSummary ImageSummary = items.get(position);

                if (delegate != null) {
                    delegate.onImageCellClicked(ImageSummary);
                    return;
                }
            }
        }
        throw new IllegalStateException(
                "RecyclerView clicked cell without tagged position or Model");
    }

}
