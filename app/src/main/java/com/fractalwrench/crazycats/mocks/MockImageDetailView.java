package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.data.ImageDetail;
import com.fractalwrench.crazycats.image.detail.ImageDetailView;


public class MockImageDetailView implements ImageDetailView {

    public String errorMessage;
    public ImageDetail imageDetail;

    @Override
    public void showProgress() {

    }

    @Override
    public void showContent(@NonNull ImageDetail ImageDetail) {
        this.imageDetail = ImageDetail;
    }

    @Override
    public void showError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
