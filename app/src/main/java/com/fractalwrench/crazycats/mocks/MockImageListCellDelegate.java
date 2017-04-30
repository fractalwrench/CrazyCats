package com.fractalwrench.crazycats.mocks;


import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.list.ImageListView;

public class MockImageListCellDelegate implements ImageListView.CellDelegate {

    public ImageData ImageData;

    @Override
    public void onImageCellClicked(ImageData ImageData) {
        this.ImageData = ImageData;
    }

}
