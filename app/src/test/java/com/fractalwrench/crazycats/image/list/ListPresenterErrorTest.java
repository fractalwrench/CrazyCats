package com.fractalwrench.crazycats.image.list;

import com.fractalwrench.crazycats.mocks.MockImageListView;
import com.fractalwrench.crazycats.mocks.TestDependencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ListPresenterErrorTest {

    private ImageListPresenter presenter;
    private MockImageListView listView;

    @Before
    public void setup() throws IOException {
        presenter = new ImageListPresenter(TestDependencies.mockFailureRepository());
        listView = new MockImageListView();
    }

    @After
    public void tearDown() {
        presenter.stop();
    }

    /**
     * Ensures that the view is notified when the presenter fails to fetch data
     */
    @Test
    public void checkPostListFailure() {
        assertNull(listView.content);
        assertNull(listView.errorMessage);

        presenter.start(listView);
        assertEquals(null, listView.content);
        assertNotNull(listView.errorMessage);
    }

}
