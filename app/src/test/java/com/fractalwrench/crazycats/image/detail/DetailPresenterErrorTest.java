package com.fractalwrench.crazycats.image.detail;


import com.fractalwrench.crazycats.mocks.MockImageDetailView;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DetailPresenterErrorTest {

    private ImageDetailPresenter presenter;
    private MockImageDetailView detailView;

    @Before
    public void setup() throws IOException {
        presenter = new ImageDetailPresenter(TestDependencies.mockFailureRepository());
        detailView = new MockImageDetailView();
    }

    @After
    public void tearDown() {
        presenter.stop();
    }

    /**
     * Ensures that the view is notified when the presenter fails to fetch data
     */
    @Test
    public void checkPostDetailFailure() {
        assertNull(detailView.imageDetail);
        assertNull(detailView.errorMessage);

        presenter.start(detailView);
        assertNull(detailView.imageDetail);
        assertNotNull(detailView.errorMessage);
    }

}
