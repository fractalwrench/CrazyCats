package com.fractalwrench.crazycats.image.detail;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.PresenterLifecycleTest;
import com.fractalwrench.crazycats.mocks.MockImageDetailView;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import java.io.IOException;

public class DetailPresenterLifecycleTest extends
                                          PresenterLifecycleTest<ImageDetailView, ImageDetailPresenter> {

    @NonNull
    @Override
    protected ImageDetailPresenter getContentViewPresenter() throws IOException {
        return new ImageDetailPresenter(TestDependencies.mockSuccessRepository());
    }

    @NonNull
    @Override
    protected ImageDetailView getContentView() {
        return new MockImageDetailView();
    }
}
