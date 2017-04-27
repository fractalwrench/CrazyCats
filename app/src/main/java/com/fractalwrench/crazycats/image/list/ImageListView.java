package com.fractalwrench.crazycats.image.list;

import android.support.annotation.Nullable;

import com.fractalwrench.crazycats.image.ContentLoadingView;
import com.fractalwrench.crazycats.image.data.ImageSummary;

import java.util.List;

/**
 * Displays a scrollable View of Images
 */
public interface ImageListView extends ContentLoadingView<List<ImageSummary>> {

    void setDelegate(@Nullable CellDelegate delegate);

    void showImageDetail(ImageSummary ImageSummary);

    interface CellDelegate {
        void onImageCellClicked(ImageSummary ImageSummary);
    }

}
