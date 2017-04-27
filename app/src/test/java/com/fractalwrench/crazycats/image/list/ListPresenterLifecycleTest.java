package com.fractalwrench.crazycats.image.list;

import android.support.annotation.NonNull;

import com.fractalwrench.crazycats.image.PresenterLifecycleTest;
import com.fractalwrench.crazycats.mocks.MockImageListView;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import java.io.IOException;

public class ListPresenterLifecycleTest extends
                                        PresenterLifecycleTest<ImageListView, ImageListPresenter> {

    @NonNull
    @Override
    protected ImageListPresenter getContentViewPresenter() throws IOException {
        return new ImageListPresenter(TestDependencies.mockSuccessRepository());
    }

    @NonNull
    @Override
    protected ImageListView getContentView() {
        return new MockImageListView();
    }

}
