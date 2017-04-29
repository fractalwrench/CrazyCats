package com.fractalwrench.crazycats.image.list;

import android.support.annotation.Nullable;

import com.fractalwrench.crazycats.image.ContentLoadingView;
import com.fractalwrench.crazycats.image.data.ImageData;

import java.util.List;

/**
 * Displays a scrollable View of Images
 */
public interface ImageListView extends ContentLoadingView<List<ImageData>> {

    void setDelegate(@Nullable CellDelegate delegate);

    void showImageDetail(ImageData imageData);

    interface CellDelegate {
        void onImageCellClicked(ImageData imageData);
    }

}
