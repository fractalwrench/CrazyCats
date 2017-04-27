package com.fractalwrench.crazycats.mocks;


import com.fractalwrench.crazycats.image.data.ImageSummary;
import com.fractalwrench.crazycats.image.list.ImageListView;

public class MockImageListCellDelegate implements ImageListView.CellDelegate {

    public ImageSummary imageSummary;

    @Override
    public void onImageCellClicked(ImageSummary ImageSummary) {
        this.imageSummary = ImageSummary;
    }


}
