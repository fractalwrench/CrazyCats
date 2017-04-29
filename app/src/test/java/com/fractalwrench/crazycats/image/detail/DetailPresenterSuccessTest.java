package com.fractalwrench.crazycats.image.detail;


import com.fractalwrench.crazycats.mocks.MockImageDetailView;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DetailPresenterSuccessTest {

    private ImageDetailPresenter presenter;
    private MockImageDetailView detailView;

    @Before
    public void setup() throws IOException {
        presenter = new ImageDetailPresenter(TestDependencies.mockSuccessRepository());
        detailView = new MockImageDetailView();
    }

    @After
    public void tearDown() {
        presenter.stop();
    }

    /**
     * Ensures that the view is notified when the presenter fetches data
     */
    @Test
    public void checkPostListFailure() {
        assertNull(detailView.imageData);
        assertNull(detailView.errorMessage);

        presenter.start(detailView);
        assertNotNull(detailView.imageData);
        assertNull(detailView.errorMessage);
    }

}
