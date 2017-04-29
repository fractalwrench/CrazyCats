package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.detail.ImageDetailView;


public class MockImageDetailView implements ImageDetailView {

    public String errorMessage;
    public ImageData imageData;

    @Override
    public void showProgress() {

    }

    @Override
    public void showContent(@NonNull ImageData ImageData) {
        this.imageData = ImageData;
    }

    @Override
    public void showError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
