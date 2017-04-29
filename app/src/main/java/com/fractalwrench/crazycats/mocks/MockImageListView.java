package com.fractalwrench.crazycats.mocks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fractalwrench.crazycats.image.data.ImageData;
import com.fractalwrench.crazycats.image.list.ImageListView;

import java.util.List;

public class MockImageListView implements ImageListView {

    public List<ImageData> content;
    public String errorMessage;

    @Override
    public void showProgress() {

    }

    @Override
    public void showContent(@NonNull List<ImageData> content) {
        this.content = content;
    }

    @Override
    public void showError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void setDelegate(@Nullable CellDelegate delegate) {

    }

    @Override
    public void showImageDetail(ImageData ImageData) {

    }
}
